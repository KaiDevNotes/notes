
function toggleNewTicketForm(){
    $("#new-ticket-form").toggleClass("hide");
}

function toggleNewMessageForm(obj){
    $(obj).siblings("form.new-message-form").toggleClass("hide");
}

function markAsResolved(ticketId){
    $("#mark-as-resolved-form input[name='ticketId']").val(ticketId);
    $("#mark-as-resolved-submit").click();
}