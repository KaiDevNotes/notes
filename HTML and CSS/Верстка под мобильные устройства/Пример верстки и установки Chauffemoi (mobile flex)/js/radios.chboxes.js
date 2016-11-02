//---------------------radios------------------
function radioClick(obj){
    var radioName = $(obj).find('input').attr('name');
    if ($(obj).children('.input-wrap').hasClass('no-checked')){
        $(obj).siblings('.radio').children('.input-wrap').removeClass('checked').addClass('no-checked');
        $('input[name='+radioName+']').attr('checked', false);
        $(obj).children('.input-wrap').removeClass('no-checked').addClass('checked');
        $(obj).find('input').attr('checked', true);
        
        //-------------change chboxes state----------
        $('#chboxes').find('.input-wrap').removeClass('checked').addClass('no-checked');
        $('#chboxes').find('input').attr('checked', false);
        if ($(obj).find('input').val() == 1 || $(obj).find('input').val() == 3){            
            $('#chboxes').find('.input-wrap').eq(1).removeClass('no-checked').addClass('checked');
            $('#chboxes').find('input').eq(1).attr('checked', true);
        }
        if ($(obj).find('input').val() == 2){            
            $('#chboxes').find('.input-wrap').eq(0).removeClass('no-checked').addClass('checked');
            $('#chboxes').find('input').eq(0).attr('checked', true);
        }
        //-----------------------
        return;
    }
}

//---------------------checkboxes------------------
function chboxClick(obj){
    if ($(obj).children('.input-wrap').hasClass('no-checked')){
        $(obj).children('.input-wrap').removeClass('no-checked').addClass('checked');
        $(obj).find('input').attr('checked', true);
        return;
    }
    if ($(obj).children('.input-wrap').hasClass('checked')){
        $(obj).children('.input-wrap').removeClass('checked').addClass('no-checked');
        $(obj).find('input').attr('checked', false);
        return;
    }
}

$(document).ready(function(){
    $('.chbox').click(function(e){
        var event = e || window.event;
        if (event.target.tagName == 'A'){
            event.target.click();
            return;
        }
        chboxClick(this);
    });
    $('.radio').click(function(){
        radioClick(this);
    });
});
