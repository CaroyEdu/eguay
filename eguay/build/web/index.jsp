<%-- 
    Document   : index
    Created on : 28-mar-2022, 10:54:36
    Author     : jean-
--%>

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
    
    <style>

.dislike-button {
  display: inline-block;
  position: relative;
  font-size: 32px;
  cursor: pointer;
  color: #ffffff;
  background : #7d7373;
   
  &::before {
    font-size: 3em;
     color: #ffffff;
    content: '♥';
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }
  &::after {
    font-size: 3em;
    content: '♥';
    position: absolute;
    left: 50%;
    top: 50%;
    color: #ffffff;
    transform: translate(-50%, -50%) scale(0);
    transition: transform 0.2s;
  }
}
.like-button {
  display: inline-block;
  position: relative;
  font-size: 32px;
  cursor: pointer;
  color: #ffffff;
  background : #7d7373;
   
  &::before {
    font-size: 3em;
     color: #ff3252;
    content: '♥';
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }
  &::after {
    font-size: 3em;
    content: '♥';
    position: absolute;
    left: 50%;
    top: 50%;
    color: #ff3252;
    transform: translate(-50%, -50%) scale(0);
    transition: transform 0.2s;
  }
}


</style>

    <% 
        List<Auction> auctionList = (List) request.getAttribute("auctionList");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
        Users user = (Users) session.getAttribute("user");
        List<Auction> auctionFavList = null;
        if(user!=null){
            auctionFavList = user.getAuctionList();
        }
    %>
    
    <body>   
        <div class="title">
            <p>¡Últimos productos!</p>
        </div>
                <%
                int cantidad = 0;
                
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
                    <img src="<%= a.getFotourl() %>" style="width:100%; height: 50%">
                    <% }else { %>
                    <img src="img/placeholder.png" style="width:100%; height: 50%">
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
                    <p><button>Pujar</button></p>
                    
                    <% if (user!=null) {
                        if(auctionFavList!= null && auctionFavList.contains(a)){
                    %>    
                    <button onclick="location.href='RegisterFavAuction?id=<%= a.getAuctionid() %>'" class="like-buttonlike-button">♥ </button>
                    <% } else{ %>
                    <button onclick="location.href='RegisterFavAuction?id=<%= a.getAuctionid() %>'" class="dislike-button">x </button>
                    <% }} %>
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
                }}
                if(cantidad > 0){
            %>
            </div>
            <%
                }
            %>
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