$(document).ready(function () {
        
    $(".dui").keypress(function () {
        let dui = $(".dui").val();
        if (dui.length == 8) {
            dui += "-";
            $(".dui").val(dui);
        }
        if (dui.length >= 9) {
            $(".dui").val(dui.substring(0, 9));
        }

    });
    $(".nit").keypress(function () {
        let nit = $(".nit").val();
        if (nit.length == 4) {
            nit += "-";
            $(".nit").val(nit);
        }
        if (nit.length == 11) {
            nit += "-";
            $(".nit").val(nit);
        }
        if (nit.length == 15) {
            nit += "-";
            $(".nit").val(nit);
        }
        if (nit.length >= 16) {
            //alert(nit.substring(0,17));
            $(".nit").val(nit.substring(0, 16));
        }

    });
    $(".viviendaPorChildren").change(function (){
        let vivienda=$(".viviendaPorChildren").val();
        if (vivienda=="2"){
            //alert("No es de children")
            $(".danosVivienda option[value=2]").attr("selected",true);                        
            //$(".danosVivienda").attr('disabled','disabled');
        }else{
            $(".danosVivienda option[value=2]").attr("selected",false);
         //   $(".danosVivienda").removeAttr('disabled');
        }
    });
    $(".NIacademico").change(function (){
        let nivele=$(".NIacademico").val();
        if (nivele=="5"){
            $(".DJEstudiar option[value=1]").attr("selected",false);                
            $(".SLAGsEstudiando option[value=1]").attr("selected",false);            
            $(".DJEstudiar option[value=2]").attr("selected",true);                                
            $(".SLAGsEstudiando option[value=2]").attr("selected",true);             
        }else{
            $(".DJEstudiar option[value=2]").attr("selected",false);                
            $(".SLAGsEstudiando option[value=2]").attr("selected",false);                        
        }
    });
    
    


});


