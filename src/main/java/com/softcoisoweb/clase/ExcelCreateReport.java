/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.clase;

import com.softcoisoweb.util.Gestor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author manue
 */
public class ExcelCreateReport {

    private final Gestor doc = new Gestor();

    private HSSFWorkbook archivoExcel = null;
    private HSSFSheet hojaArchivo = null;
    private String nombreHojaArchivo = "";
    private String nombreArchivoExcel = "";
    private List<String> encabezado = new ArrayList<String>();

    public ExcelCreateReport(final String hoja, final String nombreArchivo) {
        this.nombreHojaArchivo = hoja;
        this.nombreArchivoExcel = nombreArchivo;

    }

    public List<String> getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(List<String> encabezado) {
        this.encabezado = encabezado;
    }

    public boolean createPersona(List<reportePersona> registrosReporte) {

        this.encabezado.add("Cedula");
        this.encabezado.add("Nombre");
        this.encabezado.add("Genero");
        this.encabezado.add("Fecha Nacimiento");
        this.encabezado.add("Edad");
        this.encabezado.add("Empresa");
        this.encabezado.add("Antiguedad Empresa");
        this.encabezado.add("Cargo");
        this.encabezado.add("Fecha Clinica");
        this.encabezado.add("Telefono");
        this.encabezado.add("Correo");
        this.encabezado.add("Eps");
        this.encabezado.add("Afp");
        this.encabezado.add("Arl");
        this.encabezado.add("Contrato");
        this.encabezado.add("Organizacion Sindical");
        this.encabezado.add("Municipio");
        this.encabezado.add("Barrio");
        this.encabezado.add("Direccion");

        boolean response = false;
        this.archivoExcel = new HSSFWorkbook();
        this.hojaArchivo = this.archivoExcel.createSheet();
        this.archivoExcel.setSheetName(0, this.nombreHojaArchivo);
        CellStyle estiloEncabezadoCeldas = archivoExcel.createCellStyle();
        //Estilo Encabezado
        Font tipoFuente = archivoExcel.createFont();
        tipoFuente.setFontHeight((short) 250);
        estiloEncabezadoCeldas.setFont(tipoFuente);

        //Estilo Informacion reporte
        CellStyle estiloInfo = archivoExcel.createCellStyle();
        estiloInfo.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        estiloInfo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //Creo fila del reporte 
        HSSFRow filaEncabezadoArchivo = hojaArchivo.createRow(0);
        int numCelEncabezado = 0;
        for (String nomCellEncabe : this.encabezado) {
            HSSFCell cellEncabezado = filaEncabezadoArchivo.createCell(numCelEncabezado);
            cellEncabezado.setCellStyle(estiloEncabezadoCeldas);
            cellEncabezado.setCellValue(nomCellEncabe);
            numCelEncabezado++;
        }
        int numFile = 0;
        for (reportePersona datoCell : registrosReporte) {
            //Crea fila o row para celdas
            HSSFRow filaInfoTabla = hojaArchivo.createRow(numFile + 1);
            filaInfoTabla.createCell(0).setCellValue(datoCell.getCedula());
            filaInfoTabla.createCell(1).setCellValue(datoCell.getNombre());
            filaInfoTabla.createCell(2).setCellValue(datoCell.getGenero());
            filaInfoTabla.createCell(3).setCellValue(datoCell.getFechaNacimiento());
            filaInfoTabla.createCell(4).setCellValue(datoCell.getEdad());
            filaInfoTabla.createCell(5).setCellValue(datoCell.getEmpresa());
            filaInfoTabla.createCell(6).setCellValue(datoCell.getAntiguedadEmpresa());
            filaInfoTabla.createCell(7).setCellValue(datoCell.getCargo());
            filaInfoTabla.createCell(8).setCellValue(datoCell.getFechaClinica());
            filaInfoTabla.createCell(9).setCellValue(datoCell.getTelefono());
            filaInfoTabla.createCell(10).setCellValue(datoCell.getCorreo());
            filaInfoTabla.createCell(11).setCellValue(datoCell.getEps());
            filaInfoTabla.createCell(12).setCellValue(datoCell.getAfp());
            filaInfoTabla.createCell(13).setCellValue(datoCell.getArl());
            filaInfoTabla.createCell(14).setCellValue(datoCell.getContrato());
            filaInfoTabla.createCell(15).setCellValue(datoCell.getOrganizacion());
            filaInfoTabla.createCell(16).setCellValue(datoCell.getMunicipio());
            filaInfoTabla.createCell(17).setCellValue(datoCell.getBarrio());
            filaInfoTabla.createCell(18).setCellValue(datoCell.getDirrecion());
            numFile++;
        }
        //Genero el archivo
        try {
            try ( FileOutputStream file = new FileOutputStream(this.nombreArchivoExcel)) {
                this.archivoExcel.write(file);
            }
        } catch (IOException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error creando el excel del reporte personas, El error es: " + e);

            response = false;
        }

        return response;
    }
}
