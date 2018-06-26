<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
        <title>Support System</title>
        <link rel="stylesheet" href="/static/css/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/static/css/application.css" type="text/css" />
    </head>

    <body>
        
        <div id="app-wrapper">
            
            <#if successMessage??>
            <div class="alert alert-success">${successMessage}</div>
            </#if>
            
            <#if failureMessage??>
            <div class="alert alert-danger">${failureMessage}</div>
            </#if>
            
            <div id="customer" class="party-wrapper">
                
                <h2>
                    <span class="glyphicon glyphicon-user"></span>
                    &nbsp;
                    Customer
                    <button type="button" class="btn btn-success btn-lg pull-right" onclick="toggleNewTicketForm();">New Ticket</button>
                </h2>
                
                <br/>
                <div class="panel panel-success hide" id="new-ticket-form">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-pencil"></span>&nbsp;
                        Describe your issue
                    </div>
                    <div class="panel-body">
                        <form action="/ticket" method="POST" role="form">
                            <textarea name="issueDescription" class="form-control" rows="5"></textarea> 
                            <br/>
                            <button type="submit" class="btn btn-default">Create ticket</button> 
                            <input type="hidden" name="submitterId" value="${customer.id}"/>
                        </form>
                    </div>                    
                </div>
                
                <#list tickets as ticket>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <#if ticket.status.name() == 'NEW'>
                        <span class="label label-danger">New</span>
                        <#elseIf ticket.status.name() == 'OPENED'>
                        <span class="label label-primary">Opened</span>  
                        <#elseIf ticket.status.name() == 'RESOLVED'>
                        <span class="label label-success">Resolved</span>
                        </#if>
                        <b>Ticket</b> ${ticket.id} 
                    </div>
                    <div class="panel-body">
                        <p><strong>${ticket.issueDescription}</strong></p>
                        <#if ticket.status.name() != 'RESOLVED'>
                        <button type="submit" class="btn btn-default" onclick="toggleNewMessageForm(this);">Add message</button>  
                        <button type="submit" class="btn btn-success" onclick="markAsResolved('${ticket.id}');">Mark as resolved</button>                        
                        <form action="/ticket/message" method="POST" role="form" class="new-message-form hide">
                            <br/>
                            <textarea name="messageText" class="form-control" rows="2"></textarea> 
                            <br/>
                            <button type="submit" class="btn btn-default">Send</button>
                            <input type="hidden" name="ticketId" value="${ticket.id}"/>
                            <input type="hidden" name="senderId" value="${customer.id}"/>
                        </form>  
                        </#if>
                    </div>
                    <ul class="list-group">
                        <#list ticket.messages as message>
                        <#if message.party.name() == 'SUBMITTER'>
                        <li class="list-group-item customer-message">${message.messageText}</li>
                        <#elseIf message.party.name() == 'SUPPORT'>
                        <li class="list-group-item supprot-message">${message.messageText}</li>
                        </#if>
                        </#list>
                    </ul>
                </div>
                </#list>
                
            </div>
            
            <div id="support-engineer" class="party-wrapper">
                
                <h2>
                    <span class="glyphicon glyphicon-wrench"></span>
                    &nbsp;
                    Support Engineer
                </h2>
                
                <br/>
                <#if tickets?size == 0>
                <div class="alert alert-info">No tickets to process</div>
                </#if>
                <#list tickets as ticket>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <#if ticket.status.name() == 'NEW'>
                        <span class="label label-danger">New</span>
                        <#elseIf ticket.status.name() == 'OPENED'>
                        <span class="label label-primary">Opened</span>  
                        <#elseIf ticket.status.name() == 'RESOLVED'>
                        <span class="label label-success">Resolved</span>
                        </#if>
                        <b>Ticket</b> ${ticket.id} 
                    </div>
                    <div class="panel-body">
                        <p><strong>${ticket.issueDescription}</strong></p>
                        <#if ticket.status.name() != 'RESOLVED'>
                        <button type="submit" class="btn btn-default" onclick="toggleNewMessageForm(this);">Add message</button>  
                        <button type="submit" class="btn btn-success" onclick="markAsResolved('${ticket.id}');">Mark as resolved</button>                        
                        <form action="/ticket/message" method="POST" role="form" class="new-message-form hide">
                            <br/>
                            <textarea name="messageText" class="form-control" rows="2"></textarea> 
                            <br/>
                            <button type="submit" class="btn btn-default">Send</button>
                            <input type="hidden" name="ticketId" value="${ticket.id}"/>
                            <input type="hidden" name="senderId" value="${supportEngineer.id}"/>
                        </form> 
                        </#if>
                    </div>
                    <ul class="list-group">
                        <#list ticket.messages as message>
                        <#if message.party.name() == 'SUBMITTER'>
                        <li class="list-group-item customer-message">${message.messageText}</li>
                        <#elseIf message.party.name() == 'SUPPORT'>
                        <li class="list-group-item supprot-message">${message.messageText}</li>
                        </#if>
                        </#list>
                    </ul>
                </div>
                </#list>
                
            </div>
            
            <div class="clear"></div>
            
            <form action="/ticket/mark-as-resolved" method="POST" id="mark-as-resolved-form" class="hide">
                <input type="hidden" name="ticketId" value=""/>
                <input type="submit" name="mark-as-resolved-submit" id="mark-as-resolved-submit"/>
            </form>
            
        </div>
        
        <script type="text/javascript" src="/static/js/jquery-1.11.0.js"></script>       
        <script type="text/javascript" src="/static/js/bootstrap.min.js"></script> 
        <script type="text/javascript" src="/static/js/application.js"></script> 
    </body>
</html>