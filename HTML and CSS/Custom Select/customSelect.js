$(document).ready(function(){				

    var selTop = "<div class='selTop pie'></div>",  		    
        selDropDown = "<div class='selDropDown pie'></div>",  
        selDownArrow = "<div class='selDownArrow pie'></div>",  
        selectedOptText = "<p class='selectedOptText'></p>"; 

    $('.customSelectWrap').append(selTop).append(selDropDown).append(selDownArrow);	
    $('.selTop').append(selectedOptText); 

    var selects = $('.customSelectWrap');
    for (var i = 0; i < selects.length; i++) {
        var selectWidth = parseInt(selects.eq(i).width()),  
            selectHeight = parseInt(selects.eq(i).height()),     
            selectBGColor = selects.eq(i).css('background-color'), 
            selectBorderColor = selects.eq(i).css('borderLeftColor'),
            selectFontSize = parseInt(selects.eq(i).css('font-size')), 
            selectFontColor = selects.eq(i).css('color');			
			
        selects.eq(i).children('.selTop, .selDropDown').css({
            'width' : selectWidth				
        });
        selects.eq(i).children('.selTop').css({
            'height' : selectHeight
        });
        selects.eq(i).children('.selDropDown').css({
            'max-height' : selectWidth*2,	
            'background-color' : selectBGColor,
            'border' : '1px solid '+selectBorderColor,
            'top' : selectHeight + 2
        });
        selects.eq(i).find('.selectedOptText').css({
            'font-size' : selectFontSize,
            'color' : selectFontColor
        });
        selects.eq(i).children('.selDropDown').find('p').css({
            'font-size' : selectFontSize,
            'color' : selectFontColor
        });
        selects.eq(i).children('select').css({
            'font-size' : selectFontSize,
            'color' : selectFontColor,	
            'background-color' : selectBGColor
        });
    }	
    

    $('.selDownArrow, .selTop').click(function(){           
        $(this).parent().siblings('.customSelectWrap').children('.selDropDown').hide();
        $(this).siblings('.selDropDown').toggle();            
    });
    

    $(document).click(function(event) {            
        if ($(event.target).closest(".customSelectWrap").length) return;
        $(".selDropDown").hide();
        event.stopPropagation();
    });


    $('.customSelectWrap').each(function(){	   

        var selectedOptText = $(this).children('select').find('option:selected').text();
        $(this).find('.selectedOptText').text(selectedOptText); 

        var selOptions = $(this).children('select').find('option'); 			
        for (var i = 0; i < selOptions.length; i++) {     			
            var optText = selOptions.eq(i).text();	
            $(this).children('.selDropDown').append("<p data-opt-numb='" + i + "'>" + optText + "</p>");
        }	                             

        $(this).children('.selDropDown').children('p').click(function(){
            $(this).parent().hide();
            $(this).parent().siblings('.selTop').children('.selectedOptText').text($(this).text()); 			

            var i = $(this).attr('data-opt-numb');   
            $(this).parent().siblings('select').find('option').attr("selected", false);
            $(this).parent().siblings('select').find('option').eq(i).attr("selected", true);
        });					

    });
    

    if($(document).width() >= 1000) {          
        $('.customSelectWrap select').hide();   
    }
    else {
        $('.customSelectWrap select').show();
        $('.selDownArrow, .selTop').hide();
    }	
		
}); 