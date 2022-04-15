<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="eguay.entity.Users"%>
<%@page import="java.util.List"%>
<%@page import="eguay.entity.Auction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="cabecera.jsp"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EGUAY - Inicio</title>
    </head>
    

    <% 
        List<Auction> auctionList = (List) request.getAttribute("auctionList");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
        Users user = (Users) session.getAttribute("user");
    %>
    <style>
/* The container */
.container {
  display: block;
  position: relative;
  
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default checkbox */
.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.container input:checked ~ .checkmark {
  background-color: #2196F3;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
.container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
.container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
</style>
    <body>   
        <div class="title">
            <p>¡Últimos productos!</p>
        </div>
        <form method="POST" action="RegisterFavAuction">
                    <%
                int cantidad = 0;
                List<Auction> auctionFavList = user.getAuctionList(); 
                for(Auction a : auctionList)
                {
                    if(a.getActive()){
                    if(cantidad == 0)
                    {
            %>
            <div class="flex-container">
                <%
                    }
                    String closeDate = "";
                    if(a.getClosedate() != null){ 
                        closeDate = sdf.format(a.getClosedate());
                    }
                %>
                <div class="card">
                    <% if(!a.getFotourl().equals("") )
                    { %>
                    <img src="<%= a.getFotourl() %>" style="width:100%">
                    <% }else { %>
                    <img src="img/placeholder.png" style="width:100%">
                    <% } %>
                    <h4><a href="ProductServlet?id=<%= a.getAuctionid() %>"><%= a.getTitle() %></a></h4>
                    <p class="description"><%= a.getDescription() %></p>
                    <p class="price">$<%= a.getStartprice() %></p>
                    <% if(a.getClosedate()!=null)
                    { %>
                    <p class="description" id="cd_<%= closeDate %>"></p>
                    <% } %>
                    <% if(a.getClosenumberofbids()!=null)
                    { %>
                    <p class="description">¡Sólo quedan <%= a.getClosenumberofbids() %> pujas disponibles!</p>
                    <% } %>
                    <% if(a.getCloseprice()!=null)
                    { %>
                    <p class="description" >¡Puja <%= a.getCloseprice() %>$ y te lo llevas!</p>
                    <% } %>
                    <% if((auctionFavList != null) && (auctionFavList.contains(a))) { %>
                    <input type="checkbox" id="<%= a.getAuctionid().toString()%>" name="<%= a.getAuctionid().toString()%>" value="<%= a.getTitle()%>" checked=checked>
                    <label for="<%= a.getAuctionid().toString() %>"> 😍 </label><br>
                    
                    <% }else{ %>
                    <input type="checkbox" id="<%= a.getAuctionid().toString()%>" name="<%= a.getAuctionid().toString()%>" value="<%= a.getTitle()%>" >
                    <label for="<%= a.getAuctionid().toString() %>"> 😍 </label><br>
                    <% } %>
                </div>
            <%
                cantidad++;
                    if(cantidad == 6)
                    {
            %>
            </div>
            <%
                    cantidad = 0;
                    }
                }
                if(cantidad > 0){
            %>
            </div>
            <%
                } %>
  

<%}%>
              <input type="submit" value="Submit">
    </form>
                
        <script>
            function TimeRemaining(){
                var els = document.querySelectorAll('[id^="cd_"]');
                for (var i=0; i<els.length; i++) {
                      var el_id = els[i].getAttribute('id');
                      var end_time = el_id.split('_')[1];
                      var deadline = new Date(end_time);
                  var now = new Date();
                  var t = Math.floor(deadline.getTime() - now.getTime());
                  var days = Math.floor(t / (1000 * 60 * 60 * 24));
                  var hours = Math.floor((t % (1000 * 60 * 60 * 24))/(1000 * 60 * 60));
                  var minutes = Math.floor((t % (1000 * 60 * 60)) / (1000 * 60));
                  var seconds = Math.floor((t % (1000 * 60)) / 1000);
                  if (t < 0) {
                     document.getElementById("cd_" + end_time).innerHTML = 'EXPIRED';
                  }else{
                     document.getElementById("cd_" + end_time).innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s";
                  }
                }
             }

        function StartTimeRemaining(){
            TimeRemaining();
                setInterval(function(){
                        TimeRemaining();
                }, 1000)
        }


        StartTimeRemaining();
        </script>
    </body>
</html>
