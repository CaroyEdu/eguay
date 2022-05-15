<%-- 
    Document   : favAuction
    Created on : Apr 17, 2022, 10:53:57 AM
    Author     : Parsa zendehdel nobari
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="eguay.entity.Users"%>
<%@page import="java.util.List"%>
<%@page import="eguay.entity.Auction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        <style>
        .star {
    visibility:hidden;
    font-size:30px;
    cursor:pointer;
    pointer-events:none;  /*to make the star button uneditable*/
}
.star:before {
   content: "\2605";
   position: absolute;
   visibility:visible;
}
.star:checked:before {
   content: "\2606";
   position: absolute;
}


</style>
    <%    
           Users user = (Users) session.getAttribute("user"); 
    
           List<Auction> purchasedAuctionList = null ; 
           
           List<Auction> auctionFavList = null;

           SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
           
    %>
    
    <body>   
        <div class="title">
            <p>Pujas compradas</p>
        </div>
        <form method="POST" action="EditFavAuctionServlet">
            
            <input type="text" name="filter">
            <input type="submit" name="buscar">
        </form>
                    <%
                int cantidad = 0;
                List<Auction> favoritas =(List<Auction>) request.getAttribute("favAuctions");
                
                if(user!=null){
                purchasedAuctionList = user.getAuctionList1(); 
                auctionFavList = favoritas ; 
                }
                if(auctionFavList != null){
                for(Auction a : auctionFavList)
                {
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
                    <img src="<%= a.getFotourl() %>" style="width:10%">
                    <% }else { %>
                    <img src="img/placeholder.png" style="width:10%">
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
                    
                    <% if (user!=null) {
                        if(auctionFavList!= null && auctionFavList.contains(a)){
                    %>    
                    <input class="star" type="checkbox" title="bookmark page" name="<%=a.getAuctionid() +"Fav"%>"><br/><br/>
                    <% } else{ %>
                    <input class="star" type="checkbox" title="bookmark page" checked="unchecked" name="<%=a.getAuctionid() + "Fav" %>"><br/><br/>
                    <% }} %>
                    <br>
                    <br>
                </div>
            
            </div>
            
                <%}%> 
                <hr>
                
                <%}%>
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
