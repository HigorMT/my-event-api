package com.myevent.domain.service;

import com.myevent.common.exception.BusinessException;
import com.myevent.domain.entity.Ingresso;
import com.myevent.domain.repository.IngressoRepository;
import com.myevent.infraestruct.report.JasperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngressoService {

    private final IngressoRepository repository;
    private final JasperService jasperService;

    public List<Ingresso> findAll() {
        return repository.findAlls();
    }

    public byte[] impressaoIngresso() {
        try {
            List<Ingresso> ingressos = repository.findAlls();

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("header", "Titulo de TESTE");

            return jasperService.exportReportToPdf(parametros, ingressos, "/reports/ingresso.jasper");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }


}
