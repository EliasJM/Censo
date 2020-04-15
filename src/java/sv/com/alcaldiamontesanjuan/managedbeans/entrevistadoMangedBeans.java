package sv.com.alcaldiamontesanjuan.managedbeans;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.Basic;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import net.bootsfaces.component.row.Row;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.UploadedFile;
import sv.com.alcaldiamontesanjuan.entities.Aplica;

import sv.com.alcaldiamontesanjuan.facades.EntrevistadoFacade;
import sv.com.alcaldiamontesanjuan.facades.CaserioFacade;
import sv.com.alcaldiamontesanjuan.entities.Entrevistado;
import sv.com.alcaldiamontesanjuan.entities.Canton;
import sv.com.alcaldiamontesanjuan.entities.Caserio;
import sv.com.alcaldiamontesanjuan.entities.EstadoFamiliar;
import sv.com.alcaldiamontesanjuan.entities.EnfermedadesCronicas;
import sv.com.alcaldiamontesanjuan.entities.FamiliasConEnfermedadesCronicas;
import sv.com.alcaldiamontesanjuan.entities.FuentesDeIngreso;
import sv.com.alcaldiamontesanjuan.entities.NivelesDeEducacion;
import sv.com.alcaldiamontesanjuan.entities.ProfesionOficio;
import sv.com.alcaldiamontesanjuan.entities.ProductosAgricolas;
import sv.com.alcaldiamontesanjuan.entities.SeCultivanProductosAgricolas;
import sv.com.alcaldiamontesanjuan.facades.FamiliasConEnfermedadesCronicasFacade;
import sv.com.alcaldiamontesanjuan.facades.SeCultivanProductosAgricolasFacade;
import sv.com.alcaldiamontesanjuan.facades.IntegrantesEnfermosFacade;
import sv.com.alcaldiamontesanjuan.entities.Integrantes;
import sv.com.alcaldiamontesanjuan.entities.IntegrantesEnfermos;
import sv.com.alcaldiamontesanjuan.entities.MateriaDeAyuda;
import sv.com.alcaldiamontesanjuan.entities.NecesidadesMunicipio;
import sv.com.alcaldiamontesanjuan.entities.Parentesco;
import sv.com.alcaldiamontesanjuan.entities.Sexo;
import sv.com.alcaldiamontesanjuan.entities.Usuario;
import sv.com.alcaldiamontesanjuan.entities.Vivienda;
import sv.com.alcaldiamontesanjuan.facades.EnfermedadesCronicasFacade;
import sv.com.alcaldiamontesanjuan.facades.IntegrantesFacade;
import sv.com.alcaldiamontesanjuan.facades.NecesidadesMunicipioFacade;
import sv.com.alcaldiamontesanjuan.facades.ParentescoFacade;
import sv.com.alcaldiamontesanjuan.facades.ProductosAgricolasFacade;
import sv.com.alcaldiamontesanjuan.facades.ProfesionOficioFacade;
import sv.com.alcaldiamontesanjuan.facades.ViviendaFacade;
import sv.com.alcaldiamontesanjuan.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class entrevistadoMangedBeans {

    @EJB
    private EntrevistadoFacade entrevistadoFacade;
    @EJB
    private CaserioFacade caserioFacade;
    @EJB
    private FamiliasConEnfermedadesCronicasFacade enfermedadesFamiliaFacade;
    @EJB
    private SeCultivanProductosAgricolasFacade seCultivaFacade;
    @EJB
    private IntegrantesEnfermosFacade integrantesEnfermosFacade;
    @EJB
    private IntegrantesFacade integrantesFacade;
    @EJB
    private EnfermedadesCronicasFacade enfermedadesCronicasFacade;
    @EJB
    private ProductosAgricolasFacade productosAgricolasFacade;
    @EJB
    private ViviendaFacade viviendaFacade;
    @EJB
    private NecesidadesMunicipioFacade necesidadesDelMunicipioFacade;
    @EJB
    private ProfesionOficioFacade profesionOficioFacade;
    @EJB
    private ParentescoFacade parentescoFacade;

    private List<Caserio> listaPCanton = null;
    private List<EnfermedadesCronicas> enfermedadesLista = new ArrayList<EnfermedadesCronicas>();
    private List<ProductosAgricolas> productosAgricolasLista = new ArrayList<ProductosAgricolas>();
    private List<Integrantes> integrantesLista = new ArrayList<Integrantes>();
    private Entrevistado entrevistado = new Entrevistado();
    private EnfermedadesCronicas enfermedades = new EnfermedadesCronicas();
    private ProductosAgricolas productoAgricola = new ProductosAgricolas();
    private NecesidadesMunicipio necesidades = new NecesidadesMunicipio();
    private Vivienda vivienda = new Vivienda();
    private Integrantes integrantes = new Integrantes();
    private String[] stringForEnfermedadesIntegrantes;
    private String[] stringForEnfermedadesFamilia;
    private String[] stringfamiliasCultiva;
    private UploadedFile file;

    public Document generatePDF() throws Exception {

        Document documento = new Document();
        FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        documento.add(new Paragraph("Esto es el primer párrafo, normalito"));
        documento.add(new Paragraph("Este es el segundo y tiene una fuente rara",
                FontFactory.getFont("arial", // fuente
                        22, // tamaño
                        Font.ITALIC, // estilo
                        BaseColor.CYAN)));             // color
        PdfPTable tabla = new PdfPTable(3);
        for (int i = 0; i < 15; i++) {
            tabla.addCell("celda " + i);
        }
        documento.add(tabla);
        documento.close();
        System.out.println("aaa   " + ficheroPdf + "    ");
        return documento;
    }

    public entrevistadoMangedBeans() {
        entrevistado = new Entrevistado();
        entrevistado.setCodigoCanton(new Canton());
        entrevistado.setCodigoCaserio(new Caserio());
        entrevistado.setCodigoEstadoFamiliar(new EstadoFamiliar());
        entrevistado.setCodigoProfesionOficio(new ProfesionOficio());
        entrevistado.setCodigoNivelesEducacion(new NivelesDeEducacion());
        entrevistado.setRecibeInsumoAgricolas(new Aplica());
        entrevistado.setRecibioAyudaAsusNecesidades(new Aplica());
        entrevistado.setPoseeDiscapacidadFisica(new Aplica());
        entrevistado.setFuenteDeIngreso(new FuentesDeIngreso());
        entrevistado.setCodigoUsuario(new Usuario());
        //integrantes.setCodigoEntrevistado(entrevistado);
        integrantes = new Integrantes();
        integrantes.setCodigoEntrevistado(new Entrevistado());
        integrantes.setParentesco(new Parentesco());
        integrantes.setSexo(new Sexo());
        integrantes.setNivelAcademico(new NivelesDeEducacion());
        integrantes.setDesertoDelEstudio(new Aplica());
        integrantes.setGustariaSeguirEstudiando(new Aplica());
        necesidades = new NecesidadesMunicipio();
        necesidades.setCodigoEntrevistado(new Entrevistado());
        necesidades.setCodigoMaterialAyuda(new MateriaDeAyuda());
    }

    public List<Object[]> getConsultarIngresosPorFamilia() {
        if (getEntrevistado() != null) {
            return integrantesFacade.findIngesosPorFamilia(getEntrevistado().getId());

        } else {
            List<Object[]> objs = new ArrayList<Object[]>();
            return objs;
        }
    }

    public List<Object[]> getConsultarMayoresDeEdad() {
        if (getEntrevistado() != null) {
            return integrantesFacade.findContarMayoresDeEdad(getEntrevistado().getId());
        } else {
            List<Object[]> objs = new ArrayList<Object[]>();
            return objs;
        }
    }

    public List<Object[]> getConsultarMenoresDeEdad() {
        if (getEntrevistado() != null) {
            return integrantesFacade.findContarMenoresDeEdad(getEntrevistado().getId());
        } else {
            List<Object[]> objs = new ArrayList<Object[]>();
            return objs;
        }
    }

    public String completeEntrevistado() {

        for (EnfermedadesCronicas lista : getListaEnfermedades()) {
            FamiliasConEnfermedadesCronicas objeto = new FamiliasConEnfermedadesCronicas();
            int codig;
            do {
                codig = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
                objeto.setCodigo(String.valueOf(codig));
            } while (enfermedadesFamiliaFacade.find(String.valueOf(codig)) != null);
            objeto.setCodigoEnfermedadCronica(lista);
            objeto.setCodigoEntrevistado(entrevistado);
            enfermedadesFamiliaFacade.create(objeto);
        }
        return "personaEntrevista";
    }

    public void reiniciarValores() {
        entrevistado = new Entrevistado();
        entrevistado.setCodigoCanton(new Canton());
        entrevistado.setCodigoCaserio(new Caserio());
        entrevistado.setCodigoEstadoFamiliar(new EstadoFamiliar());
        entrevistado.setCodigoProfesionOficio(new ProfesionOficio());
        entrevistado.setCodigoNivelesEducacion(new NivelesDeEducacion());
        entrevistado.setRecibeInsumoAgricolas(new Aplica());
        entrevistado.setRecibioAyudaAsusNecesidades(new Aplica());
        entrevistado.setPoseeDiscapacidadFisica(new Aplica());
        entrevistado.setFuenteDeIngreso(new FuentesDeIngreso());
        entrevistado.setCodigoUsuario(new Usuario());
        enfermedades = new EnfermedadesCronicas();
        enfermedadesLista = new ArrayList<EnfermedadesCronicas>();
        stringfamiliasCultiva = null;
        stringForEnfermedadesFamilia = null;
    }

    public void reiniciarValoresIntegrantes() {
        //integrantes.setCodigoEntrevistado(entrevistado);
        integrantes = new Integrantes();
        integrantes.setCodigoEntrevistado(new Entrevistado());
        integrantes.setParentesco(new Parentesco());
        integrantes.setSexo(new Sexo());
        integrantes.setNivelAcademico(new NivelesDeEducacion());
        integrantes.setDesertoDelEstudio(new Aplica());
        integrantes.setGustariaSeguirEstudiando(new Aplica());
    }

    public void reiniciarValoresNecesidades() {
        necesidades = new NecesidadesMunicipio();
        necesidades.setCodigoEntrevistado(new Entrevistado());
        necesidades.setCodigoMaterialAyuda(new MateriaDeAyuda());
    }
    int contadorvar = 0;

    public List<EnfermedadesCronicas> getListaEnfermedadesParaIntegrantes() {
        if (enfermedadesLista.isEmpty()) {
            if (getEntrevistado().getId() != null) {
                enfermedadesLista = enfermedadesCronicasFacade.getListarEnfermedadesPorIntegrante(getIntegrantes().getCodigo());
            }
            return enfermedadesLista;
        } else {
            return enfermedadesLista;
        }
    }

    public List<EnfermedadesCronicas> getListaEnfermedades() {
        if (enfermedadesLista.isEmpty()) {
            return enfermedadesCronicasFacade.getListarEnfermedadesPorEntrevistado(getEntrevistado().getId());
        } else {
            return enfermedadesLista;
        }
    }

    public String setMostrarDetalles(Entrevistado objEntrevistado) {
        if (objEntrevistado != null) {
            setEntrevistado(objEntrevistado);
            return "detallesEntrevistado";
        } else {
            return "";
        }
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void uploadFile() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String cargarExcel() throws FileNotFoundException, IOException, InvalidFormatException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (getFile() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Debes agregar un archivo excel", null));
        } else {
            String extension = "";
            int i = getFile().getFileName().lastIndexOf('.');
            if (i > 0) {
                extension = getFile().getFileName().substring(i + 1);
            }
            List<String> listaErrores = new ArrayList<String>();

            Workbook libroExcel = WorkbookFactory.create(getFile().getInputstream()); //crear un libro excel
            Sheet hojaActual = libroExcel.getSheetAt(0); //acceder a la primera hoja
            if (extension.equals("xlsx") || extension.equals("xls")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha cargado el excel", null));
                if (hojaActual.getPhysicalNumberOfRows() > 1) {
                    for (int j = 1; j < hojaActual.getPhysicalNumberOfRows(); j++) {
                        org.apache.poi.ss.usermodel.Row filaActual = hojaActual.getRow(j); //acceder a la primera fila en la hoja                                                
                        if (filaActual.getCell(0) == null) {
                            listaErrores.add("La fila " + (j + 1) + " tiene un valor nullo en el campo nombre");
                        } else if (filaActual.getCell(0).getCellType() != Cell.CELL_TYPE_STRING) {
                            listaErrores.add("La fila " + (j + 1) + "  en el campo nombre no se permiten numeros");
                        }

                        if (filaActual.getCell(1) == null) {
                            listaErrores.add("La fila " + (j + 1) + " tiene un valor nullo en el campo para parentesco");
                        } else if (filaActual.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            if (parentescoFacade.find(String.valueOf((int) Math.round(filaActual.getCell(1).getNumericCellValue()))) == null) {
                                listaErrores.add("La fila " + (j + 1) + " tiene un valor no conocido en el campo parentesco: " + filaActual.getCell(1).getNumericCellValue());
                            }
                        }

                        if (filaActual.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            if (filaActual.getCell(2) == null) {
                                listaErrores.add("La fila " + (j + 1) + " tiene un valor nullo en el campo edad");
                            } else {
                                try {
                                    getIntegrantes().setEdad((int) Math.round(filaActual.getCell(2).getNumericCellValue()));
                                } catch (Exception e) {
                                    listaErrores.add("La fila " + (j + 1) + " tiene un valor no permitido en el campo edad");
                                }
                            }

                        } else {
                            listaErrores.add("La fila " + (j + 1) + " tiene un valor no permitido en el campo edad");
                        }

                        if (filaActual.getCell(3) == null) {
                            listaErrores.add("La fila " + (j + 1) + " tiene un valor nullo en el campo Genero");
                        } else if (filaActual.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            if (!String.valueOf((int) Math.round(filaActual.getCell(3).getNumericCellValue())).equals("1") && !String.valueOf((int) Math.round(filaActual.getCell(3).getNumericCellValue())).equals("2")) {
                                listaErrores.add("La fila " + (j + 1) + " tiene un valor no conocido el campo Genero");
                            }
                        }
                        if (filaActual.getCell(4) == null) {
                            listaErrores.add("La fila " + (j + 1) + " tiene un valor nullo en el campo Nivel académico");
                        } else if (filaActual.getCell(4).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            if (!String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("1")
                                    && !String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("2")
                                    && !String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("3")
                                    && !String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("4")
                                    && !String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("5")) {
                                listaErrores.add("La fila " + (j + 1) + " tiene un valor no conocido en el campo Nivel Académico");
                            }
                        } else {
                            listaErrores.add("La fila " + (j + 1) + " en el campo de Nivel academico solo se aceptan numeros");
                        }
                        if (filaActual.getCell(4).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            if (String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("5")) {
                                //se setean los demas campos
                                //se permite null en posicion 5
                                //se permite null en posicion 6
                                //se permite null en posicion 7                    
                            } else if (String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("1")
                                    || String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("2")
                                    || String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("3")
                                    || String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("4")) {
                                //Aquí se va verificar la calidad y autenticidad de las demás posiciones
                                if (filaActual.getCell(5).getCellType() == Cell.CELL_TYPE_NUMERIC) {

                                    if (String.valueOf((int) Math.round(filaActual.getCell(5).getNumericCellValue())).equals("1")) {

                                        if (filaActual.getCell(7).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                            if (String.valueOf((int) Math.round(filaActual.getCell(7).getNumericCellValue())).equals("1") || String.valueOf((int) Math.round(filaActual.getCell(7).getNumericCellValue())).equals("2")) {

                                            } else {
                                                listaErrores.add("La fila " + (j + 1) + " en el campo 'Si le brindan el apoyo le gustaria seguir estudiando' el valor ingresado no es valido'");
                                            }

                                        } else {
                                            listaErrores.add("La fila " + (j + 1) + " en el campo 'Si le brindan el apoyo le gustaria seguir estudiando' no es valido'");
                                        }
                                    } else if (String.valueOf((int) Math.round(filaActual.getCell(5).getNumericCellValue())).equals("2")) {
                                        //posición 6 es valido null
                                        //posición 7 es valido null
                                    } else {
                                        listaErrores.add("La fila " + (j + 1) + " en el campo 'Dejo de Estudiar' no es valido");
                                    }

                                } else {
                                    listaErrores.add("La fila " + (j + 1) + " en el campo 'Dejo de estudiar' no es valido'");

                                }

                            } else {
                                listaErrores.add("La fila " + (j + 1) + " en el campo nivel academico no es valido el valor " + (int) Math.round(filaActual.getCell(4).getNumericCellValue()));
                            }
                        } else {
                            listaErrores.add("La fila " + (j + 1) + " en el campo nivel academico el valor ingresado no es valido");
                        }
                        if (filaActual.getCell(8).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            if (profesionOficioFacade.find(String.valueOf((int) Math.round(filaActual.getCell(8).getNumericCellValue()))) == null) {
                                listaErrores.add("La fila " + (j + 1) + " tiene un valor no conocido en el campo profesión o Oficio");
                            }
                        } else {
                            listaErrores.add("La fila " + (j + 1) + " en el campo de profesión y oficio solo se aceptan numeros");
                        }
                        if (filaActual.getCell(9) != null) {
                            if (filaActual.getCell(9).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                                listaErrores.add("La fila " + (j + 1) + " en el campo de sueldo o ingresos mensuales solo se permiten numeros");
                            }
                        }

                    }

                }

            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Este no es un archivo permitido", null));
            }
            for (String lis : listaErrores) {
                file = null;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, lis, null));
            }
            if (listaErrores.isEmpty()) {
                Integrantes nuevoIntegrante = new Integrantes();
                for (int j = 1; j < hojaActual.getPhysicalNumberOfRows(); j++) {//inicia for
                    org.apache.poi.ss.usermodel.Row filaActual = hojaActual.getRow(j); //acceder a la primera fila en la hoja                                                
                    int codigo = 0;
                    do {
                        codigo = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
                        nuevoIntegrante.setCodigo(String.valueOf(codigo));
                    } while (integrantesFacade.find(String.valueOf(codigo)) != null);

                    nuevoIntegrante.setNombre(filaActual.getCell(0).getStringCellValue());
                    nuevoIntegrante.setParentesco(new Parentesco(String.valueOf((int) Math.round(filaActual.getCell(1).getNumericCellValue()))));
                    nuevoIntegrante.setEdad((int) Math.round(filaActual.getCell(2).getNumericCellValue()));
                    nuevoIntegrante.setSexo(new Sexo(String.valueOf((int) Math.round(filaActual.getCell(3).getNumericCellValue()))));
                    nuevoIntegrante.setNivelAcademico(new NivelesDeEducacion(String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue()))));
                    if (String.valueOf((int) Math.round(filaActual.getCell(4).getNumericCellValue())).equals("5")) {
                        nuevoIntegrante.setDesertoDelEstudio(new Aplica("2"));
                        nuevoIntegrante.setGustariaSeguirEstudiando(new Aplica("2"));
                    } else {
                        //si ha estudiando algo
                        nuevoIntegrante.setDesertoDelEstudio(new Aplica(String.valueOf((int) Math.round(filaActual.getCell(5).getNumericCellValue()))));
                        if (String.valueOf((int) Math.round(filaActual.getCell(5).getNumericCellValue())).equals("1")) {//si dejo de estudiar
                            nuevoIntegrante.setGustariaSeguirEstudiando(new Aplica(String.valueOf((int) Math.round(filaActual.getCell(7).getNumericCellValue()))));//aquí se va a 
                        } else {
                            nuevoIntegrante.setGustariaSeguirEstudiando(new Aplica("2"));
                        }
                        if (filaActual.getCell(6) != null) {
                            switch (filaActual.getCell(6).getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    nuevoIntegrante.setDetalleDePDeserto(String.valueOf((int) Math.round(filaActual.getCell(6).getNumericCellValue())));
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    nuevoIntegrante.setDetalleDePDeserto(String.valueOf(filaActual.getCell(6).getStringCellValue()));
                                    break;
                            }
                        } else {
                            nuevoIntegrante.setDetalleDePDeserto("");
                        }
                    }
                    nuevoIntegrante.setCodigoEntrevistado(getEntrevistado());
                    nuevoIntegrante.setSePuedeDesempenar(new ProfesionOficio(String.valueOf((int) Math.round(filaActual.getCell(8).getNumericCellValue()))));
                    if (filaActual.getCell(9) == null) {
                        nuevoIntegrante.setIngresosMensuales(0);
                    } else {
                        if (filaActual.getCell(9).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            nuevoIntegrante.setIngresosMensuales((int) Math.round(filaActual.getCell(9).getNumericCellValue()));
                        } else {
                            nuevoIntegrante.setIngresosMensuales(0);
                        }
                    }

                    integrantesFacade.create(nuevoIntegrante);
                    nuevoIntegrante = new Integrantes();
                    nuevoIntegrante.setCodigoEntrevistado(new Entrevistado());
                    nuevoIntegrante.setParentesco(new Parentesco());
                    nuevoIntegrante.setSexo(new Sexo());
                    nuevoIntegrante.setNivelAcademico(new NivelesDeEducacion());
                    nuevoIntegrante.setDesertoDelEstudio(new Aplica());
                    nuevoIntegrante.setGustariaSeguirEstudiando(new Aplica());
                }
                file = null;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardaron con exito los integrantes", null));
            }
            return "";

        }
        return "";
    }//fin

    public String cargarDatosAgricolas() {
        int contador = 0;

        if (getEntrevistado().getId() != null) {
            productosAgricolasLista = productosAgricolasFacade.getListarProductosAgricolasPorEntrevistado(getEntrevistado().getId());
            String[] stringTmp = new String[productosAgricolasLista.size()];
            for (ProductosAgricolas tmpLista : productosAgricolasLista) {
                stringTmp[contador] = tmpLista.getCodigo();
                contador++;
            }
            setStringfamiliasCultiva(stringTmp);
            cargarDatosVivienda();
            cargarDatosEnfermedades();
        } else {
            return "personaEntrevista";
        }

        return "estaFamiliaCultiva";
    }

    public void cargarDatosVivienda() {
        FacesContext context = FacesContext.getCurrentInstance();
        Vivienda tmp = viviendaFacade.getListarViviendaPorEntrevistado(getEntrevistado().getId());
        if (tmp == null) {
            setVivienda(new Vivienda());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!!!! no se han encrontrado registros", null));
        } else {
            setVivienda(tmp);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modifique los datos que desea", null));
        }

    }

    public String cargarDatosEnfermedades() {
        int contador = 0;
        if (getEntrevistado().getId() != null) {
            List<EnfermedadesCronicas> lista = enfermedadesCronicasFacade.getListarEnfermedadesPorEntrevistado(getEntrevistado().getId());

            String[] stringTmp = new String[lista.size()];
            for (EnfermedadesCronicas tmpLista : lista) {
                stringTmp[contador] = tmpLista.getCodigo();
                contador++;
            }
            setStringForEnfermedadesFamilia(stringTmp);
        } else {
            return "personaEntrevista";
        }
        return "familiasConEnfermedades";
    }

    public List<NecesidadesMunicipio> getListarNecesidadesPorFamilia() {
        List<NecesidadesMunicipio> lista = necesidadesDelMunicipioFacade.listarNecesidadesPorFamilia(getEntrevistado().getId());
        return lista;
    }

    public String eliminarNececidadesPorFamilia() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            NecesidadesMunicipio tmp = necesidadesDelMunicipioFacade.find(getNecesidades().getCodigo());
            if (tmp != null) {
                necesidadesDelMunicipioFacade.remove(getNecesidades());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado con exito!!", null));
                reiniciarValoresNecesidades();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha encontrado este registro en la base de datos", null));
            }
        } catch (NullPointerException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String modificarNecesidadesFamilia() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (getNecesidades() != null) {
            if (necesidadesDelMunicipioFacade.find(getNecesidades().getCodigo()) == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede encontrar este registro en la Base de Datos.", null));
                reiniciarValoresNecesidades();
            } else {
                necesidadesDelMunicipioFacade.edit(getNecesidades());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado con exito", null));
                reiniciarValoresNecesidades();
            }
            return "estaFamiliaNecesita";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha enviado nada en esta petición.", null));
        }

        return "estaFamiliaNecesita";
    }

    public String insertNecesidadesFamilia() {
        FacesContext context = FacesContext.getCurrentInstance();
        getNecesidades().setCodigoEntrevistado(getEntrevistado());
        int codigo = 0;
        do {
            codigo = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
            getNecesidades().setCodigo(String.valueOf(codigo));

        } while (necesidadesDelMunicipioFacade.find(String.valueOf(codigo)) != null);
        try {
            necesidadesDelMunicipioFacade.create(getNecesidades());
            reiniciarValoresNecesidades();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Petición exitosa, Guardado con exito", null));
        } catch (EJBException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error abortado!! " + e.getMessage() + ". NO SE PUDO PROCESAR ESTA PETICIÓN", null));
            reiniciarValoresNecesidades();
        }
        return "estaFamiliaNecesita";
    }

    public String insert() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String codigoEntrevista = "";
        //Crea una codigo de ficha con el codigo del cantón y caserío
        codigoEntrevista = getEntrevistado().getCodigoCanton().getCodigo() + "-" + getEntrevistado().getCodigoCaserio().getCodigoCorrelativo() + "-" + getEntrevistado().getCodigoEntrevista();        
        Entrevistado tmpControl = entrevistadoFacade.getEntrevistadoPorFicha(codigoEntrevista);//verifica si la ficha existe en la base de datos
        if (tmpControl == null) {
            int codigo = 0;
            do {
                codigo = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
                getEntrevistado().setId(String.valueOf(codigo));//genera una llave random
            } while (entrevistadoFacade.find(String.valueOf(codigo)) != null);
            if (entrevistadoFacade.find(getEntrevistado().getId()) == null) {
                try {                    
                    //se ejecuta cuando no existe la llave primaria en la BD
                    entrevistado.getRecibioAyudaAsusNecesidades().setCodigo("2");
                    java.util.Date fecha = new Date();
                    entrevistado.setFechaRegistro(fecha);
                    getEntrevistado().setCodigoEntrevista(codigoEntrevista);
                    HttpSession session = SessionUtils.getSession();
                    getEntrevistado().setId(String.valueOf(codigo));
                    entrevistadoFacade.create(getEntrevistado());//se agrega a la base de datos datos del entrevistado                    
                    getIntegrantes().setNombre(getEntrevistado().getNombre());
                    getIntegrantes().setNivelAcademico(getEntrevistado().getCodigoNivelesEducacion());
                    getIntegrantes().setCodigoEntrevistado(getEntrevistado());
                    getIntegrantes().setSePuedeDesempenar(getEntrevistado().getCodigoProfesionOficio());
                    //getIntegrantes().set
                    do {
                        codigo = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
                        getIntegrantes().setCodigo(String.valueOf(codigo));
                    } while (integrantesFacade.find(String.valueOf(codigo)) != null);
                    if (integrantesFacade.find(getIntegrantes().getCodigo()) == null) {
                        try {
                            integrantesFacade.create(getIntegrantes());
                            guardarEnfermedadesPorIntegrante();
                            guardarListaEnfermedadesPorFamilia();
                            //aquí
                            guardarListaProductosAgricolasPorFamilia();
                            //aquí
                            reiniciarValoresIntegrantes();
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entrevistado guardo con exito", null));
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tambien se guardo como Integrante", null));

                        } catch (EJBException e) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error abortado!! " + e.getMessage() + ". NO SE PUDO GUARDAR ESTE INTEGRANTE", null));
                        }
                    }

                    //reiniciarValores();
                    return "vivienda";
                } catch (EJBException e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error abortado!! " + e.getMessage() + ". Intente nuevamente, si el problema persiste reinicie los campos.", null));
                }
            }
        } else {//difrente de null
            if (getEntrevistado().getId() == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! este codigo ficha " + getEntrevistado().getCodigoEntrevista() + " ya existe en estos registros", null));
            } else {
                if (entrevistadoFacade.find(getEntrevistado().getId()) == null) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede Agregar este nuevo entrevistado.", null));
                    reiniciarValores();
                    context.getExternalContext().redirect("personaEntrevista.xhtml");

                } 
            }

        }
        return "";
    }

    public List<Entrevistado> getListarEntrevistados() {
        List<Entrevistado> lista = entrevistadoFacade.findAll();
        return lista;
    }

    public List<Entrevistado> getListarEntrevistadosPorUsuario(String usuarioCodigo) {
        List<Entrevistado> lista = entrevistadoFacade.getEntrevistadoPorUsuarioFindAll(usuarioCodigo);
        return lista;
    }

    public List<Integrantes> getListarIntegrantesFindAll() {
        List<Integrantes> lista = integrantesFacade.findAll();
        return lista;
    }

    public String setModIntegrante(Integrantes integrante) {
        this.integrantes = integrante;
        List<IntegrantesEnfermos> lista = new ArrayList<IntegrantesEnfermos>();
        int contador = 0;
        if (integrante != null) {
            if (integrante.getCodigo() != null) {
                lista = integrantesEnfermosFacade.getListarEnfermedadesPorIntegrante(integrante.getCodigo());
            }
        }
        String[] stringTmp = new String[lista.size()];
        for (IntegrantesEnfermos integrantesEnfermos : lista) {
            stringTmp[contador] = integrantesEnfermos.getDiscapacidadEnfermedad().getCodigo();
            contador++;
        }
        setStringForEnfermedadesIntegrantes(stringTmp);
        return "integranteModificar";
    }

    public void setListaPorCanton(List<Caserio> lista) {
        this.setListaPCanton(lista);
    }

    public List<Caserio> getConsultarPorCanton() {
        List<Caserio> lista = new ArrayList<Caserio>();
        try {
            if (entrevistado.getCodigoCanton().getCodigo() != null || !"".equals(entrevistado.getCodigoCanton().getCodigo())) {
                String codigo = entrevistado.getCodigoCanton().getCodigo();
                listaPCanton = caserioFacade.consultarPorCanton(codigo);
                return listaPCanton;
            } else {
                lista = caserioFacade.findAll();
                return lista;

            }

        } catch (NullPointerException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!!! " + e.getMessage(), null));
            return null;
        }

    }

    public String eliminar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Entrevistado tmp = entrevistadoFacade.find(getEntrevistado().getId());
            if (tmp.getBeneficioPoblacionCollection().size() > 0
                    || tmp.getIntegrantesCollection().size() > 0
                    || tmp.getFamiliasConEnfermedadesCronicasCollection().size() > 0
                    || tmp.getNecesidadesMunicipioCollection().size() > 0
                    || tmp.getSeCultivanProductosAgricolasCollection().size() > 0
                    || tmp.getViviendaCollection().size() > 0) {

                tmp.getBeneficioPoblacionCollection().clear();
                tmp.getViviendaCollection().clear();
                tmp.getIntegrantesCollection().clear();
                tmp.getFamiliasConEnfermedadesCronicasCollection().clear();
                tmp.getNecesidadesMunicipioCollection().clear();
                tmp.getSeCultivanProductosAgricolasCollection().clear();
                entrevistadoFacade.remove(getEntrevistado());
                reiniciarValores();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entrevistado eliminado con exito", null));

            } else {
                entrevistadoFacade.remove(getEntrevistado());
                reiniciarValores();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entrevistado eliminado con exito", null));
            }
        } catch (NullPointerException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String eliminarIntegrantes() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Integrantes tmp = integrantesFacade.find(getIntegrantes().getCodigo());
            if (tmp != null) {
                integrantesFacade.remove(tmp);
                reiniciarValoresIntegrantes();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado este Integrante con exito", null));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha encontrado este registro en la base de datos", null));
            }
        } catch (NullPointerException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha podido eliminar", null));
        }
        return "";
    }

    public String eliminarEnfermedades(EnfermedadesCronicas enfermedad) {
        FacesContext context = FacesContext.getCurrentInstance();
        FamiliasConEnfermedadesCronicas familia = new FamiliasConEnfermedadesCronicas();

        if (!enfermedad.getCodigo().equals("") && !getEntrevistado().getId().equals("")) {
            //familia entrevista y lista de enfermedades
            familia = enfermedadesFamiliaFacade.getVerificarExistencias(enfermedad.getCodigo(), getEntrevistado().getId());
            if (familia != null) {
                enfermedadesFamiliaFacade.remove(familia);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino con exito", null));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha encontrado esta enfermedad en esta familia", null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se puede procesar petición", null));
        }
        return "";
    }

    public String eliminarProductoAgricola(ProductosAgricolas producto) {
        FacesContext context = FacesContext.getCurrentInstance();
        SeCultivanProductosAgricolas cultivan = new SeCultivanProductosAgricolas();

        if (!getEntrevistado().getId().equals("") && !producto.getCodigo().equals("")) {
            cultivan = seCultivaFacade.getVerificarExistencias(producto.getCodigo(), getEntrevistado().getId());
            if (cultivan != null) {
                seCultivaFacade.remove(cultivan);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino con exito", null));
                productosAgricolasLista = new ArrayList<ProductosAgricolas>();

            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se ha encontrado este producto en esta familia", null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!! no se puede procesar la petición", null));
        }

        return "";
    }

    public String modificar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (entrevistadoFacade.find(getEntrevistado().getId()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar el codigo.<br/> Eliminelo y ingrese un nuevo Entrevistado con ese codigo.", null));
            reiniciarValores();
        } else {
            entrevistadoFacade.edit(getEntrevistado());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entrevistado modificado con Exito", null));
            reiniciarValores();
        }
        return "personaEntrevista";
    }

    public String modificarIntegrante() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (integrantesFacade.find(getIntegrantes().getCodigo()) == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede modificar.<br/>", null));

            reiniciarValoresIntegrantes();
            stringForEnfermedadesFamilia = null;
        } else {
            integrantesFacade.edit(getIntegrantes());
            guardarEnfermedadesPorIntegrante();//metodo que guarda los valores en la base de datos                
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Integrante modificado con Exito", null));
            reiniciarValoresIntegrantes();
            stringForEnfermedadesFamilia = null;
        }
        return "integrantes";
    }

    public Entrevistado getEntrevistado() {
        return entrevistado;
    }

    public String setEntrevistado(Entrevistado entrevistado) {
        this.entrevistado = entrevistado;
        return "personaEntrevistaModificar";
    }

    public String setEntrevistadoRemove(Entrevistado entrevistado) {
        this.entrevistado = entrevistado;
        return "";
    }

    public String setEntrevistadoDetallesCompletos(Entrevistado objEntrevistado) {
        if (objEntrevistado != null) {
            setEntrevistado(objEntrevistado);
            return "detallesCompletos";
        } else {
            return "";
        }
    }

    public String habilitarPermisoEdicion(Entrevistado objEntrevistado) {
        if (objEntrevistado.getId()!=null) {
            objEntrevistado.setPermisos("edit");
            entrevistadoFacade.edit(objEntrevistado);            
        }
        return "";
    }
    public String habilitarTodosPermisos(Entrevistado objEntrevistado) {
        if (objEntrevistado.getId()!=null) {
            objEntrevistado.setPermisos("todos");
            entrevistadoFacade.edit(objEntrevistado);            
        }
        return "";
    }
    public String bloquearTodosPermisos(Entrevistado objEntrevistado) {
        if (objEntrevistado.getId()!=null) {
            objEntrevistado.setPermisos("ter");
            entrevistadoFacade.edit(objEntrevistado);            
        }
        return "";
    }

    public String terminarProceso() {
        if (getEntrevistado().getId() != null) {
            Entrevistado tmp1 = entrevistadoFacade.find(getEntrevistado().getId());
            if (tmp1.getViviendaCollection().size() > 0 && tmp1.getIntegrantesCollection().size() > 0) {
                tmp1.setPermisos("ter");
                entrevistadoFacade.edit(tmp1);
            }
            reiniciarValores();
        } else {
            reiniciarValores();
        }
        return "personaEntrevista";
    }

    public String guardarIntegrantesYEnfermedades() {
        integrantes.setCodigoEntrevistado(entrevistado);
        int codigo = 0;
        FacesContext context = FacesContext.getCurrentInstance();
        do {
            codigo = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
            getIntegrantes().setCodigo(String.valueOf(codigo));
        } while (integrantesFacade.find(String.valueOf(codigo)) != null);
        if (integrantesFacade.find(getIntegrantes().getCodigo()) == null) {
            try {
                integrantesFacade.create(getIntegrantes());
                guardarEnfermedadesPorIntegrante();//metodo que guarda los valores en la base de datos
                reiniciarValoresIntegrantes();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Integrante guardo con exito", null));

            } catch (EJBException e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error abortado!! " + e.getMessage() + ". NO SE PUDO GUARDAR ESTE INTEGRANTE", null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Integrante no se puede guardar. Modifique el codigo " + getIntegrantes().getCodigo() + " para poder guardar", null));
        }
        return "integrantes";
    }

    public void guardarEnfermedadesPorIntegrante() {
        FacesContext context = FacesContext.getCurrentInstance();
        int codig = 0, contador = 0;
        List<IntegrantesEnfermos> lista = integrantesEnfermosFacade.getListarEnfermedadesPorIntegrante(getIntegrantes().getCodigo());
        for (IntegrantesEnfermos integrantesEnfermos : lista) {
            integrantesEnfermosFacade.remove(integrantesEnfermos);
        }
        for (String enfermedad : getStringForEnfermedadesIntegrantes()) {
            contador++;
            IntegrantesEnfermos objeto = new IntegrantesEnfermos();
            do {
                codig = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
                objeto.setCodigo(String.valueOf(codig));
            } while (integrantesEnfermosFacade.find(String.valueOf(codig)) != null);
            objeto.setCodigoIntegrante(getIntegrantes());
            objeto.setDiscapacidadEnfermedad(new EnfermedadesCronicas(enfermedad));
            integrantesEnfermosFacade.create(objeto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado la enfermedad " + contador + ", de Integrante: " + getIntegrantes().getNombre(), null));
        }
        stringForEnfermedadesIntegrantes = null;

    }

    public void setIntegrante(Integrantes i) {
        this.integrantes = i;

    }

    public String terminarIntegrantes() {
        return "personaEntrevista";
    }

    public List<Integrantes> consultarIntegrantesPorEntrevistado() {
        List<Integrantes> lista = new ArrayList<Integrantes>();
        if (getEntrevistado().getId() != null || getEntrevistado().getId() != "") {
            lista = integrantesFacade.findPorEntrevistado(getEntrevistado().getId());
        }
        return lista;
    }

    public List<IntegrantesEnfermos> getListarEnfermedadesPorIntegrante(Integrantes integrante) {
        List<IntegrantesEnfermos> lista = new ArrayList<IntegrantesEnfermos>();
        if (integrante != null) {
            if (integrante.getCodigo() != null) {
                lista = integrantesEnfermosFacade.getListarEnfermedadesPorIntegrante(integrante.getCodigo());
            }
        }
        return lista;
    }

    public String setEntrevistadoDetalle(Entrevistado entrevistado) {
        this.entrevistado = entrevistado;
        Entrevistado tmp = entrevistadoFacade.find(entrevistado.getId());
        if (tmp.getViviendaCollection().size() > 0) {
            return "completarNuevamenteDatos";
        } else {
            Vivienda tmpvivienda = viviendaFacade.getListarViviendaPorEntrevistado(getEntrevistado().getId());
            if (tmpvivienda != null) {
                return "completarNuevamenteDatos";
            } else {
                return "vivienda";
            }
        }
    }

    public List<Caserio> getListaPCanton() {
        return listaPCanton;
    }

    public void setListaPCanton(List<Caserio> listaPCanton) {
        this.listaPCanton = listaPCanton;
    }

    public EnfermedadesCronicas getEnfermedades() {
        return enfermedades;
    }

    public void guardarListaEnfermedadesPorFamilia() {
        int codigo = 0;
        List<FamiliasConEnfermedadesCronicas> lista = enfermedadesFamiliaFacade.listarEnfermedadesDeIntegrante(getEntrevistado().getId());
        if (lista != null) {
            for (FamiliasConEnfermedadesCronicas familiasConEnfermedadesCronicas : lista) {
                enfermedadesFamiliaFacade.remove(familiasConEnfermedadesCronicas);
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        int contador = 0;
        for (String idEnfermedad : stringForEnfermedadesFamilia) {
            contador++;
            FamiliasConEnfermedadesCronicas obj = new FamiliasConEnfermedadesCronicas();
            do {
                codigo = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
                obj.setCodigo(String.valueOf(codigo));
            } while (enfermedadesFamiliaFacade.find(String.valueOf(codigo)) != null);
            obj.setCodigoEnfermedadCronica(new EnfermedadesCronicas(idEnfermedad));
            obj.setCodigoEntrevistado(getEntrevistado());
            enfermedadesFamiliaFacade.create(obj);
        }
        stringForEnfermedadesFamilia = null;
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardaron " + contador + " enfermedades que padece esta familia", null));

    }

    public void guardarListaProductosAgricolasPorFamilia() {
        int codigo = 0;
        List<SeCultivanProductosAgricolas> lista = seCultivaFacade.listarProductosAgricolas(getEntrevistado().getId());
        if (lista != null) {
            for (SeCultivanProductosAgricolas seCultivanProductosAgricolas : lista) {
                seCultivaFacade.remove(seCultivanProductosAgricolas);
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        int contador = 0;
        for (String idProducto : stringfamiliasCultiva) {
            contador++;
            SeCultivanProductosAgricolas obj = new SeCultivanProductosAgricolas();
            do {
                codigo = (int) (Math.random() * (1999999999 - 1 + 1) + 1);
                obj.setCodigo(String.valueOf(codigo));
            } while (enfermedadesFamiliaFacade.find(String.valueOf(codigo)) != null);
            obj.setCodigoEntrevistado(getEntrevistado());
            obj.setCodigoPoducto(new ProductosAgricolas(idProducto));
            seCultivaFacade.create(obj);
        }
        stringfamiliasCultiva = null;
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardaron " + contador + " productos Agricolas", null));

    }

    public String guardarListaEnfermedadesFamilia() {
        guardarListaEnfermedadesPorFamilia();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardaron las enfermedades correctamente", null));
        return "completarNuevamenteDatos";
    }

    public String guardarListaProductosAgricolas() {
        guardarListaProductosAgricolasPorFamilia();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardaron los productos agricolas correctamente", null));
        return "completarNuevamenteDatos";
    }

    public void setEnfermedades2(EnfermedadesCronicas enfermedades) {
        this.enfermedades = enfermedades;
        int contador = 0;
        FacesContext context = FacesContext.getCurrentInstance();
        for (int i = 0; i < enfermedadesLista.size(); i++) {
            if (enfermedades.equals(enfermedadesLista.get(i))) {
                contador++;
            }
        }
        if (contador == 0) {
            enfermedadesLista.add(getEnfermedades());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Esta enfermedad: " + enfermedades.getNombre() + " se agrego con exito ", null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Esta enfermedad: " + enfermedades.getNombre() + " ya la agrego a esta lista", null));
        }

    }

    public void setProductoAgricola(ProductosAgricolas producto) {
        this.productoAgricola = producto;
        int contador = 0;
        for (int i = 0; i < productosAgricolasLista.size(); i++) {

            if (producto.equals(productosAgricolasLista.get(i))) {
                contador++;
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        if (contador == 0) {
            productosAgricolasLista.add(producto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agrego correctamente: " + productoAgricola.getNombre() + " ", null));
        } else {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este producto Agricola: " + productoAgricola.getNombre() + " ya la agrego a esta lista", null));
        }
    }

    /**
     * @return the integrantes
     */
    public Integrantes getIntegrantes() {
        return integrantes;
    }

    /**
     * @param integrantes the integrantes to set
     */
    public void setIntegrantes(Integrantes integrantes) {
        this.integrantes = integrantes;
    }

    /**
     * @return the stringForEnfermedadesFamilia
     */
    public String[] getStringForEnfermedadesFamilia() {
        return stringForEnfermedadesFamilia;
    }

    /**
     * @param stringForEnfermedadesFamilia the stringForEnfermedadesFamilia to
     * set
     */
    public void setStringForEnfermedadesFamilia(String[] stringForEnfermedadesFamilia) {
        this.stringForEnfermedadesFamilia = stringForEnfermedadesFamilia;
    }

    public String[] getStringForEnfermedadesIntegrantes() {
        return stringForEnfermedadesIntegrantes;
    }

    public void setStringForEnfermedadesIntegrantes(String[] stringForEnfermedadesIntegrantes) {
        this.stringForEnfermedadesIntegrantes = stringForEnfermedadesIntegrantes;
    }

    public String[] getStringfamiliasCultiva() {
        return stringfamiliasCultiva;
    }

    public void setStringfamiliasCultiva(String[] stringfamiliasCultiva) {
        this.stringfamiliasCultiva = stringfamiliasCultiva;
    }

    /**
     * @return the necesidades
     */
    public NecesidadesMunicipio getNecesidades() {
        return necesidades;
    }

    /**
     * @param necesidades the necesidades to set
     */
    public void setNecesidades(NecesidadesMunicipio necesidades) {
        this.necesidades = necesidades;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void onItemUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, msg);
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

}
