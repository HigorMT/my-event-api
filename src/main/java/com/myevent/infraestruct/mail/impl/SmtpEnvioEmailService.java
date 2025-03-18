package com.myevent.infraestruct.mail.impl;

import com.myevent.api.dto.system.email.Mensagem;
import com.myevent.common.email.EmailProperties;
import com.myevent.common.exception.EmailException;
import com.myevent.domain.entity.Usuario;
import com.myevent.infraestruct.mail.SendEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
@RequiredArgsConstructor
public class SmtpEnvioEmailService implements SendEmailService {

    private final Configuration freeMarkerConfiguration;
    private final EmailProperties emailProperties;
    private final JavaMailSender mailSender;

    @Override
    public void send(Mensagem mensagem) {
        try {
            String template = proccessTemplate(mensagem);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(mensagem.getDestinatario());
            helper.setSubject(mensagem.getAssunto());
            helper.setText(template, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar o e-mail", e);
        }
    }

    private String proccessTemplate(Mensagem mensagem) {
        try {
            Template template = freeMarkerConfiguration.getTemplate(mensagem.getContent());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getModels());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail.", e);
        }
    }

    /**
     * Implementação padrão para envio de e-mail de recuperação de senha de {@link Usuario}
     *
     * @param destinatario E-mail do destinatário
     * @param link         Link para a recuperação de senha
     */
    public void sendMailRecoveryPass(String destinatario, String link) {
        Mensagem mensagem = Mensagem.builder()
                .assunto("Email de Recuperação de Senha.")
                .content("reset-password.html")
                .model("link", link)
                .destinatario(destinatario)
                .build();

        send(mensagem);
    }


}
