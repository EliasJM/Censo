package sv.com.alcaldiamontesanjuan.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import sv.com.alcaldiamontesanjuan.facades.FamiliasConEnfermedadesCronicasFacade;
import sv.com.alcaldiamontesanjuan.entities.FamiliasConEnfermedadesCronicas;
import sv.com.alcaldiamontesanjuan.facades.EntrevistadoFacade;
import sv.com.alcaldiamontesanjuan.facades.IntegrantesFacade;
import sv.com.alcaldiamontesanjuan.facades.ViviendaFacade;

@ManagedBean
@SessionScoped
public class graficosManagedBeans {

    @EJB
    private FamiliasConEnfermedadesCronicasFacade familiasConEnfermedadesFacade;
    @EJB
    private EntrevistadoFacade entrevistadoFacade;

    @EJB
    private IntegrantesFacade integrantesFacade;
    @EJB
    private ViviendaFacade viviendaFacade;

    private BarChartModel barModel;
    private BarChartModel barModelProfesionOficio;
    private BarChartModel barModelNivelesEducacion;
    private BarChartModel barModelPoseeDiscapacidad;
    private BarChartModel barModelRecibeInsumosAgricolas;
    private BarChartModel barModelFuentesDeIngreso;
    private BarChartModel barModelPoseeEscrituras;
    private BarChartModel barModelPredioMunicipal;
    private BarChartModel barModelRegistrada;
    private BarChartModel barModelTenenciaVivienda;
    private BarChartModel barModelConstruidaPorChildren;
    private BarChartModel barModelDanosEnTecho;
    private BarChartModel barModelDanosEnPared;
    private BarChartModel barModelDanosEnPuerta;
    private BarChartModel barModelDanosEnVentana;
    private BarChartModel barModelMaterialVivienda;
    private BarChartModel barModelTipoTecho;
    private BarChartModel barModelServicioAgua;
    private BarChartModel barModelServicioEnergiaElectrica;
    private BarChartModel barModelServicioAseo;
    private BarChartModel barModelPersonasDejaronEstudiar;
    private BarChartModel barModelPersonasGustariaSegirEstudiando;
    private BarChartModel barModelProfesionMasElegida;
    private BarChartModel barModelEnfermedadesMasFrecuentes;
    private BarChartModel barModelIntegrantesNivelEducacion;
    private PieChartModel model;

    @PostConstruct
    public void init() {
        createBarModel();
        createBarModelVivienda();
        createBarModelIntegrantes();
        vistaPies();
    }

    private void vistaPies() {
        model = new PieChartModel();
        model.set("Brand 1", 540);
        model.set("Brand 2", 325);
        model.set("Brand 3", 702);
        model.set("Brand 4", 421);
        model.setTitle("Simple Pie");
        model.setLegendPosition("w");

    }

    public PieChartModel getModel() {
        return model;
    }

    //metodos modelos
    private void createBarModelIntegrantes() {
        barModelPersonasDejaronEstudiar = initBarModelPersonasDejaronDeEstudiar();
        barModelPersonasDejaronEstudiar.setTitle("Conteo de personas que dejaron de estudiar.");
        barModelPersonasDejaronEstudiar.setLegendPosition("ne");
        barModelPersonasDejaronEstudiar.setMouseoverHighlight(true);
        barModelPersonasDejaronEstudiar.setShowDatatip(true);
        barModelPersonasDejaronEstudiar.setShowPointLabels(true);
        Axis xAxisSE = barModelPersonasDejaronEstudiar.getAxis(AxisType.X);
        Axis yAxisSE = barModelPersonasDejaronEstudiar.getAxis(AxisType.Y);
        yAxisSE.setLabel("Cantidad de Personas");
        yAxisSE.setMin(0);
        barModelPersonasGustariaSegirEstudiando = initBarModelPersonasGustariaVolverAEsrudiar();
        barModelPersonasGustariaSegirEstudiando.setTitle("Conteo de personas que dejaron de estudiar pero les gustaria seguir estudiando.");
        barModelPersonasGustariaSegirEstudiando.setLegendPosition("ne");
        barModelPersonasGustariaSegirEstudiando.setMouseoverHighlight(true);
        barModelPersonasGustariaSegirEstudiando.setShowDatatip(true);

        barModelPersonasGustariaSegirEstudiando.setShowPointLabels(true);
        Axis xAxisGSE = barModelPersonasGustariaSegirEstudiando.getAxis(AxisType.X);
        Axis yAxisGSE = barModelPersonasGustariaSegirEstudiando.getAxis(AxisType.Y);
        yAxisGSE.setLabel("Cantidad de Personas");
        yAxisGSE.setMin(0);
        barModelProfesionMasElegida = initBarModelProfesionMasElegidaIntegrantes();
        barModelProfesionMasElegida.setTitle("Conteo de profesiones mas elegidas por los integrantes de los grupos familiares.");
        barModelProfesionMasElegida.setLegendPosition("ne");
        barModelProfesionMasElegida.setMouseoverHighlight(true);
        barModelProfesionMasElegida.setShowDatatip(true);
        barModelProfesionMasElegida.setShowPointLabels(true);
        Axis xAxisPOME = barModelProfesionMasElegida.getAxis(AxisType.X);
        Axis yAxisPOME = barModelProfesionMasElegida.getAxis(AxisType.Y);
        yAxisPOME.setLabel("Cantidad de Personas");
        yAxisPOME.setMin(0);
        barModelEnfermedadesMasFrecuentes = initBarModelEnfermedadesMasFrecuentesIntegrantes();
        barModelEnfermedadesMasFrecuentes.setTitle("Conteo de Enfermedades cronicas más frecuentes en los integrantes de este municipio");
        barModelEnfermedadesMasFrecuentes.setLegendPosition("ne");
        barModelEnfermedadesMasFrecuentes.setMouseoverHighlight(true);
        barModelEnfermedadesMasFrecuentes.setShowDatatip(true);
        barModelEnfermedadesMasFrecuentes.setShowPointLabels(true);

        Axis xAxisEMF = barModelEnfermedadesMasFrecuentes.getAxis(AxisType.X);
        Axis yAxisEMF = barModelEnfermedadesMasFrecuentes.getAxis(AxisType.Y);
        yAxisEMF.setLabel("Cantidad de Personas");
        yAxisEMF.setMin(0);
        barModelIntegrantesNivelEducacion = initBarModelNivelesEducacionIntegrantes();
        barModelIntegrantesNivelEducacion.setTitle("Graficas de los niveles academicos de las personas integrantes");
        barModelIntegrantesNivelEducacion.setLegendPosition("ne");
        barModelIntegrantesNivelEducacion.setMouseoverHighlight(true);
        barModelIntegrantesNivelEducacion.setShowDatatip(true);
        barModelIntegrantesNivelEducacion.setShowPointLabels(true);
        Axis xAxisINE = barModelIntegrantesNivelEducacion.getAxis(AxisType.X);
        Axis yAxisINE = barModelIntegrantesNivelEducacion.getAxis(AxisType.Y);
        yAxisINE.setLabel("Cantidad de Personas");
        yAxisINE.setMin(0);

    }

    private BarChartModel initBarModelNivelesEducacionIntegrantes() {
        List<Object[]> lista = integrantesFacade.getPersonasNivelAcademicoIntegrantes();
        BarChartModel model = new BarChartModel();
        List<BarChartSeries> listaBCS = new ArrayList<BarChartSeries>();
        BarChartSeries boys = new BarChartSeries();
        String label = "";
        for (Object[] obj : lista) {
            boys = new BarChartSeries();
            label = String.valueOf(obj[0]);
            boys.setLabel(label);
            boys.set(String.valueOf(obj[0]), (Number) obj[1]);
            model.addSeries(boys);
        }

        return model;
    }

    private BarChartModel initBarModelEnfermedadesMasFrecuentesIntegrantes() {
        List<Object[]> lista = integrantesFacade.getConteoEnfermedadesIntegrantes();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            BarChartSeries boys = new BarChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelProfesionMasElegidaIntegrantes() {
        List<Object[]> lista = integrantesFacade.getContarProfesionOficioIntegrantes();
        BarChartModel model = new BarChartModel();
        BarChartSeries boys = new BarChartSeries();
        for (Object[] obj : lista) {
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
            boys = new BarChartSeries();
        }
        return model;
    }

    private BarChartModel initBarModelPersonasGustariaVolverAEsrudiar() {
        List<Object[]> lista = integrantesFacade.getPersonasGustariaSeguirEstudiando();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            BarChartSeries boys = new BarChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelPersonasDejaronDeEstudiar() {
        List<Object[]> lista = integrantesFacade.getContarPersonasDejanDeEstudiar();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            BarChartSeries boys = new BarChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private void createBarModel() {
        barModel = initBarModelParaEnfermedades();
        barModel.setTitle("Reporte sobre Enfermedades");
        barModel.setLegendPosition("ne");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Enfermedad");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad de familias");
        yAxis.setMin(0);

        barModelProfesionOficio = initBarModelProfesionOficio();
        barModelProfesionOficio.setTitle("Reporte de Profesiones o Oficios");
        barModelProfesionOficio.setLegendPosition("ne");
        Axis xAxisPO = barModelProfesionOficio.getAxis(AxisType.X);
        xAxisPO.setLabel("Profesión o Oficio");
        Axis yAxisPO = barModelProfesionOficio.getAxis(AxisType.Y);
        yAxisPO.setLabel("Cantidad de Familias");
        yAxisPO.setMin(0);

        barModelNivelesEducacion = initBarModelNivelesEducacionEntrevistados();
        barModelNivelesEducacion.setTitle("Reporte de los Niveles de Educación en Entrevistados");
        barModelNivelesEducacion.setLegendPosition("ne");
        Axis xAxisNE = barModelNivelesEducacion.getAxis(AxisType.X);
        xAxisNE.setLabel("Niveles Educación");
        Axis yAxisNE = barModelNivelesEducacion.getAxis(AxisType.Y);
        yAxisNE.setLabel("Cantidad de Familias");
        yAxisNE.setMin(0);

        barModelPoseeDiscapacidad = initBarModelPoseeDiscapacidadEntrevistado();
        barModelPoseeDiscapacidad.setTitle("Reporte de la cantidad de personas discapacitadas");
        barModelPoseeDiscapacidad.setLegendPosition("ne");
        Axis xAxisPD = barModelPoseeDiscapacidad.getAxis(AxisType.X);
        xAxisPD.setLabel("Discapacitados");
        Axis yAxisPD = barModelPoseeDiscapacidad.getAxis(AxisType.Y);
        yAxisPD.setLabel("Cantidad de Familias");
        yAxisPD.setMin(0);
        barModelRecibeInsumosAgricolas = initBarModelResibeInsumosAgricolas();
        barModelRecibeInsumosAgricolas.setTitle("Reporte de las familias que reciben Insumos Agrícolas");
        barModelRecibeInsumosAgricolas.setLegendPosition("ne");
        Axis xAxisRIA = barModelRecibeInsumosAgricolas.getAxis(AxisType.X);
        xAxisRIA.setLabel("Discapacitados");
        Axis yAxisRIA = barModelRecibeInsumosAgricolas.getAxis(AxisType.Y);
        yAxisRIA.setLabel("Cantidad de Familias");
        yAxisRIA.setMin(0);

        barModelFuentesDeIngreso = initBarModelFuentedeIngreso();
        barModelFuentesDeIngreso.setTitle("Reporte de la principal fuente de ingreso de las familias");
        barModelFuentesDeIngreso.setLegendPosition("ne");
        Axis xAxisFI = barModelFuentesDeIngreso.getAxis(AxisType.X);
        xAxisFI.setLabel("Fuente de Ingreso");
        Axis yAxisFI = barModelFuentesDeIngreso.getAxis(AxisType.Y);
        yAxisFI.setLabel("Cantidad de Familias");
        yAxisFI.setMin(0);
    }

    public void createBarModelVivienda() {
        barModelPoseeEscrituras = initBarModelViviendaPoseeEscrituras();
        barModelPoseeEscrituras.setTitle("Reporte de los Hogares que Poseen Escrituras");
        barModelPoseeEscrituras.setLegendPosition("ne");
        Axis xAxisPEs = barModelPoseeEscrituras.getAxis(AxisType.X);
        xAxisPEs.setLabel("Fuente de Ingreso");
        Axis yAxisPEs = barModelPoseeEscrituras.getAxis(AxisType.Y);
        yAxisPEs.setLabel("Cantidad de Hogares");
        yAxisPEs.setMin(0);
        barModelPredioMunicipal = initBarModelPredioMunicipal();
        barModelPredioMunicipal.setTitle("Reporte de los Hogares en Predio Municipal");
        barModelPredioMunicipal.setLegendPosition("ne");
        Axis xAxisPM = barModelPredioMunicipal.getAxis(AxisType.X);
        Axis yAxisPM = barModelPredioMunicipal.getAxis(AxisType.Y);
        yAxisPM.setLabel("Cantidad de Hogares");
        yAxisPM.setMin(0);
        barModelRegistrada = initBarModelRegistrada();
        barModelRegistrada.setTitle("Reporte de los Hogares con escrituras registradas");
        barModelRegistrada.setLegendPosition("ne");
        Axis xAxisR = barModelRegistrada.getAxis(AxisType.X);
        Axis yAxisR = barModelRegistrada.getAxis(AxisType.Y);
        yAxisR.setLabel("Cantidad de Hogares");
        yAxisR.setMin(0);
        barModelTenenciaVivienda = initBarModelTenenciaDeVivienda();
        barModelTenenciaVivienda.setTitle("Reporte de los entrevistados por Tenencia de Vivienda");
        barModelTenenciaVivienda.setLegendPosition("ne");
        Axis xAxisTV = barModelTenenciaVivienda.getAxis(AxisType.X);
        Axis yAxisTV = barModelTenenciaVivienda.getAxis(AxisType.Y);
        yAxisTV.setLabel("Cantidad de Hogares");
        yAxisTV.setMin(0);
        barModelConstruidaPorChildren = initBarModelConstruidaPorChildren();
        barModelConstruidaPorChildren.setTitle("Reporte Viviendas construida por Children");
        barModelConstruidaPorChildren.setLegendPosition("ne");
        Axis xAxisVCC = barModelConstruidaPorChildren.getAxis(AxisType.X);
        Axis yAxisVCC = barModelConstruidaPorChildren.getAxis(AxisType.Y);
        yAxisVCC.setLabel("Cantidad de Hogares");
        yAxisVCC.setMin(0);
        barModelDanosEnTecho = initBarModelDanosTecho();
        barModelDanosEnTecho.setTitle("Reporte Viviendas con daños en sus Techos");
        barModelDanosEnTecho.setLegendPosition("ne");
        Axis xAxisDA = barModelDanosEnTecho.getAxis(AxisType.X);
        Axis yAxisDA = barModelDanosEnTecho.getAxis(AxisType.Y);
        yAxisDA.setLabel("Cantidad de Hogares");
        yAxisDA.setMin(0);
        barModelDanosEnPared = initBarModelDanosParedes();
        barModelDanosEnPared.setTitle("Reporte Viviendas con daños en sus Paredes");
        barModelDanosEnPared.setLegendPosition("ne");
        Axis xAxisDPa = barModelDanosEnPared.getAxis(AxisType.X);
        Axis yAxisDPa = barModelDanosEnPared.getAxis(AxisType.Y);
        yAxisDPa.setLabel("Cantidad de Hogares");
        yAxisDPa.setMin(0);
        barModelDanosEnPuerta = initBarModelPuertasDanadas();
        barModelDanosEnPuerta.setTitle("Reporte Viviendas con daños en sus Puertas");
        barModelDanosEnPuerta.setLegendPosition("ne");
        Axis xAxisDPu = barModelDanosEnPuerta.getAxis(AxisType.X);
        Axis yAxisDPu = barModelDanosEnPuerta.getAxis(AxisType.Y);
        yAxisDPu.setLabel("Cantidad de Hogares");
        yAxisDPu.setMin(0);
        barModelDanosEnVentana = initBarModelDanosVentena();
        barModelDanosEnVentana.setTitle("Reporte Viviendas con daños en sus ventanas");
        barModelDanosEnVentana.setLegendPosition("ne");
        Axis xAxisDV = barModelDanosEnVentana.getAxis(AxisType.X);
        Axis yAxisDV = barModelDanosEnVentana.getAxis(AxisType.Y);
        yAxisDV.setLabel("Cantidad de Hogares");
        yAxisDV.setMin(0);
        barModelMaterialVivienda = initBarModelMaterialVivienda();
        barModelMaterialVivienda.setTitle("Reporte de los materiales con los que estan construidas las viviendas");
        barModelMaterialVivienda.setLegendPosition("ne");
        Axis xAxisMV = barModelMaterialVivienda.getAxis(AxisType.X);
        Axis yAxisMV = barModelMaterialVivienda.getAxis(AxisType.Y);
        yAxisMV.setLabel("Cantidad de Hogares");
        yAxisMV.setMin(0);
        barModelTipoTecho = initBarModelTipoTecho();
        barModelTipoTecho.setTitle("Reporte de los tipos de techos instalados en las viviendas");
        barModelTipoTecho.setLegendPosition("ne");
        Axis xAxisTT = barModelTipoTecho.getAxis(AxisType.X);
        Axis yAxisTT = barModelTipoTecho.getAxis(AxisType.Y);
        yAxisTT.setLabel("Cantidad de Hogares");
        yAxisTT.setMin(0);
        barModelServicioAgua = initBarModelServicioDeAgua();
        barModelServicioAgua.setTitle("Conteo de las viviendas que cuentan servicio de Agua Potable");
        barModelServicioAgua.setLegendPosition("ne");
        Axis xAxisSA = barModelServicioAgua.getAxis(AxisType.X);
        Axis yAxisSA = barModelServicioAgua.getAxis(AxisType.Y);
        yAxisSA.setLabel("Cantidad de Viviendas");
        yAxisSA.setMin(0);
        barModelServicioAseo = initBarModelServicioDeAseo();
        barModelServicioAseo.setTitle("Conteo de las viviendas que cuentan con servicio de aseo");
        barModelServicioAseo.setLegendPosition("ne");
        Axis xAxisSAs = barModelServicioAseo.getAxis(AxisType.X);
        Axis yAxisSAs = barModelServicioAseo.getAxis(AxisType.Y);
        Axis yAxis = barModelServicioAseo.getAxis(AxisType.Y);
        yAxisSAs.setLabel("Cantidad de Viviendas");
        yAxisSAs.setMin(0);

        barModelServicioEnergiaElectrica = initBarModelServicioEnergiaElectrica();
        barModelServicioEnergiaElectrica.setTitle("Reporte de las viviendas que cuentan con servicio de energía eléctrica");
        barModelServicioEnergiaElectrica.setLegendPosition("ne");
        barModelServicioEnergiaElectrica.setMouseoverHighlight(true);
        barModelServicioEnergiaElectrica.setShowDatatip(true);
        barModelServicioEnergiaElectrica.setShowPointLabels(true);
        Axis xAxisSE = barModelServicioEnergiaElectrica.getAxis(AxisType.X);
        Axis yAxisSE = barModelServicioEnergiaElectrica.getAxis(AxisType.Y);
        yAxisSE.setLabel("Cantidad de Hogares");
        yAxisSE.setMin(0);
    }

    //metodos inicializar modelo  
    private BarChartModel initBarModelServicioDeAseo() {
        List<Object[]> lista = viviendaFacade.getContarServicioDeAseo();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            BarChartSeries boys = new BarChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelServicioEnergiaElectrica() {
        List<Object[]> lista = viviendaFacade.getContarEnergiaElectrica();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelServicioDeAgua() {
        List<Object[]> lista = viviendaFacade.getContarPoseeAguaPotable();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelMaterialVivienda() {
        List<Object[]> lista = viviendaFacade.getContarMaterialDeVivienda();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelTipoTecho() {
        List<Object[]> lista = viviendaFacade.getContarTipoDeTecho();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelDanosTecho() {
        List<Object[]> lista = viviendaFacade.getContarTechoDanado();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelDanosVentena() {
        List<Object[]> lista = viviendaFacade.getContarVentanasDanadas();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelDanosParedes() {
        List<Object[]> lista = viviendaFacade.getContarParedesDanadas();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelPuertasDanadas() {
        List<Object[]> lista = viviendaFacade.getContarPuertasDanadas();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelTenenciaDeVivienda() {
        List<Object[]> lista = viviendaFacade.getContarTenenciaDeVivienda();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelConstruidaPorChildren() {
        List<Object[]> lista = viviendaFacade.getContarConstruidaPorChildren();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelRegistrada() {
        List<Object[]> lista = viviendaFacade.getContarRegistrada();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelPredioMunicipal() {
        List<Object[]> lista = viviendaFacade.getContarPredioMunicipal();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelViviendaPoseeEscrituras() {
        List<Object[]> lista = viviendaFacade.getContarPoseeEscrituras();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelParaEnfermedades() {
        List<Object[]> lista = familiasConEnfermedadesFacade.getlistarEnfermedadesGBy();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries boys = new ChartSeries();
            boys.setLabel(String.valueOf(obj[0]));
            boys.set(obj[0], (Number) obj[1]);
            model.addSeries(boys);
        }
        return model;
    }

    private BarChartModel initBarModelProfesionOficio() {
        List<Object[]> lista = entrevistadoFacade.getContarProfesionOficio();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries category = new ChartSeries();
            category.setLabel(String.valueOf(obj[0]));
            category.set(obj[0], (Number) obj[1]);
            model.addSeries(category);
        }
        return model;
    }

    private BarChartModel initBarModelNivelesEducacionEntrevistados() {
        List<Object[]> lista = entrevistadoFacade.getContarNivelesEducacion();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries category = new ChartSeries();
            category.setLabel(String.valueOf(obj[0]));
            category.set(obj[0], (Number) obj[1]);
            model.addSeries(category);
        }
        return model;
    }

    private BarChartModel initBarModelPoseeDiscapacidadEntrevistado() {
        List<Object[]> lista = entrevistadoFacade.getContarPoseeDiscapacidad();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries category = new ChartSeries();
            category.setLabel(String.valueOf(obj[0]));
            category.set(obj[0], (Number) obj[1]);
            model.addSeries(category);
        }
        return model;
    }

    private BarChartModel initBarModelResibeInsumosAgricolas() {
        List<Object[]> lista = entrevistadoFacade.getContarRecibeInsumosAgricolas();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries category = new ChartSeries();
            category.setLabel(String.valueOf(obj[0]));
            category.set(obj[0], (Number) obj[1]);
            model.addSeries(category);
        }
        return model;
    }

    private BarChartModel initBarModelFuentedeIngreso() {
        List<Object[]> lista = entrevistadoFacade.getContarFuenteDeIngreso();
        BarChartModel model = new BarChartModel();
        for (Object[] obj : lista) {
            ChartSeries category = new ChartSeries();
            category.setLabel(String.valueOf(obj[0]));
            category.set(obj[0], (Number) obj[1]);
            model.addSeries(category);
        }
        return model;
    }

    //inicio metodos mostraGraficos
    public String mostrarGraficoEnfermedades() {

        return "MostrarGrafico";
    }

    //metodos get y set
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel getBarModelNivelesEducacion() {
        return barModelNivelesEducacion;
    }

    public BarChartModel getBarModelProfesionOficio() {
        return barModelProfesionOficio;
    }

    public BarChartModel getBarModelPoseeDiscapacidad() {
        return barModelPoseeDiscapacidad;
    }

    public BarChartModel getBarModelRecibeInsumosAgricolas() {
        return barModelRecibeInsumosAgricolas;
    }

    public BarChartModel getBarModelFuentesDeIngreso() {
        return barModelFuentesDeIngreso;
    }

    public BarChartModel getBarModelPoseeEscrituras() {
        return barModelPoseeEscrituras;
    }

    public BarChartModel getBarModelRegistrada() {
        return barModelRegistrada;
    }

    public void setBarModelRegistrada(BarChartModel barModelRegistrada) {
        this.barModelRegistrada = barModelRegistrada;
    }

    public BarChartModel getBarModelTenenciaVivienda() {
        return barModelTenenciaVivienda;
    }

    public void setBarModelTenenciaVivienda(BarChartModel barModelTenenciaVivienda) {
        this.barModelTenenciaVivienda = barModelTenenciaVivienda;
    }

    public BarChartModel getBarModelConstruidaPorChildren() {
        return barModelConstruidaPorChildren;
    }

    public void setBarModelConstruidaPorChildren(BarChartModel barModelConstruidaPorChildren) {
        this.barModelConstruidaPorChildren = barModelConstruidaPorChildren;
    }

    public BarChartModel getBarModelPredioMunicipal() {
        return barModelPredioMunicipal;
    }

    public void setBarModelPredioMunicipal(BarChartModel barModelPredioMunicipal) {
        this.barModelPredioMunicipal = barModelPredioMunicipal;
    }

    public BarChartModel getBarModelDanosEnTecho() {
        return barModelDanosEnTecho;
    }

    public void setBarModelDanosEnTecho(BarChartModel barModelDanosEnTecho) {
        this.barModelDanosEnTecho = barModelDanosEnTecho;
    }

    public BarChartModel getBarModelDanosEnPared() {
        return barModelDanosEnPared;
    }

    public void setBarModelDanosEnPared(BarChartModel barModelDanosEnPared) {
        this.barModelDanosEnPared = barModelDanosEnPared;
    }

    public BarChartModel getBarModelDanosEnPuerta() {
        return barModelDanosEnPuerta;
    }

    public void setBarModelDanosEnPuerta(BarChartModel barModelDanosEnPuerta) {
        this.barModelDanosEnPuerta = barModelDanosEnPuerta;
    }

    public BarChartModel getBarModelDanosEnVentana() {
        return barModelDanosEnVentana;
    }

    public void setBarModelDanosEnVentana(BarChartModel barModelDanosEnVentana) {
        this.barModelDanosEnVentana = barModelDanosEnVentana;
    }

    public BarChartModel getBarModelMaterialVivienda() {
        return barModelMaterialVivienda;
    }

    public void setBarModelMaterialVivienda(BarChartModel barModelMaterialVivienda) {
        this.barModelMaterialVivienda = barModelMaterialVivienda;
    }

    public BarChartModel getBarModelTipoTecho() {
        return barModelTipoTecho;
    }

    public void setBarModelTipoTecho(BarChartModel barModelTipoTecho) {
        this.barModelTipoTecho = barModelTipoTecho;
    }

    public BarChartModel getBarModelServicioAgua() {
        return barModelServicioAgua;
    }

    public void setBarModelServicioAgua(BarChartModel barModelServicioAgua) {
        this.barModelServicioAgua = barModelServicioAgua;
    }

    public BarChartModel getBarModelServicioEnergiaElectrica() {
        return barModelServicioEnergiaElectrica;
    }

    public void setBarModelServicioEnergiaElectrica(BarChartModel barModelServicioEnergiaElectrica) {
        this.barModelServicioEnergiaElectrica = barModelServicioEnergiaElectrica;
    }

    public BarChartModel getBarModelServicioAseo() {
        return barModelServicioAseo;
    }

    public void setBarModelServicioAseo(BarChartModel barModelServicioAseo) {
        this.barModelServicioAseo = barModelServicioAseo;
    }

    public BarChartModel getBarModelPersonasDejaronEstudiar() {
        return barModelPersonasDejaronEstudiar;
    }

    public void setBarModelPersonasDejaronEstudiar(BarChartModel barModelPersonasDejaronEstudiar) {
        this.barModelPersonasDejaronEstudiar = barModelPersonasDejaronEstudiar;
    }

    public BarChartModel getBarModelPersonasGustariaSegirEstudiando() {
        return barModelPersonasGustariaSegirEstudiando;
    }

    public void setBarModelPersonasGustariaSegirEstudiando(BarChartModel barModelPersonasGustariaSegirEstudiando) {
        this.barModelPersonasGustariaSegirEstudiando = barModelPersonasGustariaSegirEstudiando;
    }

    public BarChartModel getBarModelProfesionMasElegida() {
        return barModelProfesionMasElegida;
    }

    public void setBarModelProfesionMasElegida(BarChartModel barModelProfesionMasElegida) {
        this.barModelProfesionMasElegida = barModelProfesionMasElegida;
    }

    public BarChartModel getBarModelEnfermedadesMasFrecuentes() {
        return barModelEnfermedadesMasFrecuentes;
    }

    public void setBarModelEnfermedadesMasFrecuentes(BarChartModel barModelEnfermedadesMasFrecuentes) {
        this.barModelEnfermedadesMasFrecuentes = barModelEnfermedadesMasFrecuentes;
    }

    /**
     * @return the barModelIntegrantesNivelEducacion
     */
    public BarChartModel getBarModelIntegrantesNivelEducacion() {
        return barModelIntegrantesNivelEducacion;
    }

    /**
     * @param barModelIntegrantesNivelEducacion the
     * barModelIntegrantesNivelEducacion to set
     */
    public void setBarModelIntegrantesNivelEducacion(BarChartModel barModelIntegrantesNivelEducacion) {
        this.barModelIntegrantesNivelEducacion = barModelIntegrantesNivelEducacion;
    }
}
