package com.myevent.infraestruct.mail;

import com.myevent.api.dto.system.email.Mensagem;

public interface SendEmailService {

    void send(Mensagem mensagem);

}

