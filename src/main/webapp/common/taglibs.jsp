<%@ page import="com.framework.core.common.SysConstants" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var jsBasePath = "${path}";  
</script>
<%
  long sysInitTime = SysConstants.SYS_INIT_TIME;
  request.setAttribute("sysInitTime",sysInitTime);
%>

