package view.beans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import java.sql.Connection;

import java.util.HashMap;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.ExcelDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.data.XlsDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;

public class JasperReportBean {
    public JasperReportBean() {
    }

    public String runReport_action() {
        try {
            runReport("emp.jasper", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String runReport_action123() {
        try {
            //XLS, XLSX
            File f =
                new File("C:\\JDeveloper\\mywork\\testJasperReport\\ViewController\\public_html\\Reports\\Product_List");

           // XlsDataSource xlsDs = new XlsDataSource(f);
            JasperReport jasperReport = JasperCompileManager.compileReport("xls.jrxml");
            JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, new HashMap(), new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "sample.pdf");


            File f2 = new File("XmlDataSource.xml");
            JRXmlDataSource JprXmlDsr = new JRXmlDataSource(f, "DataList/Line");

            /* Compile the template */
            JasperReport Rep = JasperCompileManager.compileReport("MyReport.jrxml");

            /* Create the JasperPrint object with the template and the data */
            JasperPrint Prn = JasperFillManager.fillReport(Rep, new HashMap(), JprXmlDsr);

            /* Export the report to pdf format if needed*/
            JasperExportManager.exportReportToPdfFile(Prn, "MyReport.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void runReport(String repPath, java.util.Map param) throws Exception {
        Connection conn = null;
        try {
            HttpServletResponse response = getResponse();
            ServletOutputStream out = response.getOutputStream();
            response.setHeader("Cache-Control", "max-age=0");
            response.setContentType("application/pdf");
            ServletContext context = getContext();
            InputStream fs = context.getResourceAsStream("/Reports/" + repPath);
            JasperReport template = (JasperReport) JRLoader.loadObject(fs);
            template.setWhenNoDataType(WhenNoDataTypeEnum.NO_DATA_SECTION);
            conn = getConnection();
            JasperPrint print = JasperFillManager.fillReport(template, param, conn);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, baos);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception jex) {
            jex.printStackTrace();
        } finally {
            close(conn);
        }
    }

    public Connection getDataSourceConnection(String dataSourceName) throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup(dataSourceName);
        return ds.getConnection();
    }

    private Connection getConnection() throws Exception {
        return getDataSourceConnection("java:comp/env/jdbc/hrDS");
    }

    public ServletContext getContext() {
        return (ServletContext) getFacesContext().getExternalContext().getContext();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void runActionReport(ActionEvent actionEvent) {
        try {
            runReport("emp.jasper", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String waelAbdeenJaper() {
        // Add event code here...
        return null;
    }
}
