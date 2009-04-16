<%@ page import="com.sonatype.training.babble.services.BabbleManager"  %>
<%@ page import="com.sonatype.training.babble.domain.Babbler"  %>
<%@ page import="com.sonatype.training.babble.domain.Babble"  %>
<%@ page import="java.util.List"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="f" %> 

<%
//
// In a real application, this would go in a controller
//
String user = request.getParameter("username");
Babbler babbler = (Babbler) session.getAttribute("babbler");

String action = request.getParameter("action");
if ("logout".equals(action)) {
    session.removeAttribute("babbler");
    babbler = null;
}

if ("login".equals(action)) {
	if ((user != null) && (user.trim().length() > 0)) {
		//
		// Logging on or registering
		//
		
		babbler = BabbleManager.getInstance().findByName(user);
		if (babbler == null) {
			babbler = BabbleManager.getInstance().register(user);
		}
		session.setAttribute("babbler",babbler);
	} 
}

if ("babble".equals(action)) {
	String message = request.getParameter("message");
	if ((message != null) && (message.trim().length() > 0) && (babbler != null)) {
	    babbler.utterBabble(message);   
	}
}
%>

<html>
<body>
<h2><f:message key="app.title"/></h2>
<p><em>Version <f:message key="app.version"/></em></p>

<% 
if (babbler == null) {
%>
<h3><f:message key="app.login"/></h3>
<form method="get">
  <table border="0">
    <tr>
      <td>What's your username?</td>
      <td><input name="username" type="text"></td>
      <td>&nbsp;</td><td><input name="action" value="login" type="submit"></td>
    </tr>
  </table>
</form>
<%
} else {
%>

<h3>Hi <%=babbler.getName()%>! <f:message key="app.utter.babble"/></h3>

<form method="get">
  <table border="0">
    <tr>
      <td>Message</td><td><input name="message" type="text"></td>
    </tr>
    <tr>
      <td>&nbsp;</td><td><input name="action" type="submit" value="babble"></td>
    </tr>
  </table>
</form>
<form method="get">
    <input name="action" type="submit" value="logout">
</form>

<%
} 
%>
<h3><f:message key="app.latest.babbles"/></h3>

        <hr>
<%
      List<Babble> babbles = BabbleManager.getInstance().findAllBabbles();
      for (Babble babble : babbles) { 
%>
        <p><b><%=babble.getBabbler().getName()%></b>: <%=babble.getUtterence()%></p>       
        <hr>
<% 
      }
%>
</body>
</html>