package com.myevent.infraestruct.report;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class JasperService {

    public byte[] exportReportToPdf(Map<String, Object> parametros, String resource) throws JRException {
        InputStream inputStream = this.getClass().getResourceAsStream(resource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JREmptyDataSource());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] exportReportToPdf(Map<String, Object> parametros, List<?> detail, String resource) throws JRException, IllegalArgumentException {
        InputStream inputStream = this.getClass().getResourceAsStream(resource);

        if (isNull(inputStream)) {
            throw new IllegalArgumentException("Arquivo não encontrado no classpath: " + resource);
        }

        JRDataSource dataSource = new JRBeanCollectionDataSource(detail);
        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] exportReportJrxmlToPdf(Map<String, Object> parametros, List<?> detail, String resource) throws JRException, IllegalArgumentException {
        InputStream inputStream = this.getClass().getResourceAsStream(resource);

        if (isNull(inputStream)) {
            throw new IllegalArgumentException("Arquivo não encontrado no classpath: " + resource);
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRDataSource dataSource = new JRBeanCollectionDataSource(detail);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}