    function objAttrToCss(obj){  
        // устанавливаем высоту элемента пропорционально ширине.
        if ($(obj).attr('data-height') != undefined){
            var hPercent = parseInt($(obj).attr('data-height'));
            var hPixels = Math.floor(($(obj).width()*hPercent)/100);
            $(obj).css({
                'height' : hPixels+'px'
            });
        } 
        // задаем вертикальное выравнивание по-центру при аблолютном позиционировании.
        if ($(obj).attr('data-absolute-v-center') != undefined){
            var mTop = -Math.floor(($(obj).height())/2);
            $(obj).css({
                'margin-top' : mTop+'px',
                'top' : $(obj).attr('data-absolute-v-center')   // если есть padding, то величину top необходимо подбирать эксперементально                    
            });
        } 
        // задаем горизонтальное выравнивание по-центру при аблолютном позиционировании.
        if ($(obj).attr('data-absolute-h-center') != undefined){
            var mLeft = -Math.floor(($(obj).width())/2);
            $(obj).css({
                'margin-left' : mLeft+'px',
                'left' : $(obj).attr('data-absolute-h-center')   // если есть padding, то величину left необходимо подбирать эксперементально                    
            });
        }
    }

    // Чтобы установить размер шрифта в блоке 16px при ширине экрана 400px 
    // необходимо укзать ему значение font-size: 16%, т.к. 100% - это 100px
    function setBodyFontSize(){
        var bodyFontSize = '100'; // 100px - размер шрифта при начальной ширине экрана = initWindowWidth
        var initWindowWidth = '400'; //400px
        var correlation = bodyFontSize / initWindowWidth;
        var flexibleFontSize = Math.floor(($(window).width())*correlation);
        $('body').css({
            'font-size' : flexibleFontSize+'px'     
        });                                         
    }
    
    function setContentWidth(){
        $('meta[name=viewport]').attr('content','width='+$(window).width()+', user-scalable=no');
    }
    
    function initCss(){
        //setContentWidth();
        setBodyFontSize();
        $('*').each(function(){
            objAttrToCss($(this));
        });
    }    
    

    $(document).ready(function(){ 
        initCss();
    });

    $(window).resize(function(){ 
        initCss();              
    });
    