<%@ include file="../tag/tagInclude.jsp" %>

<div style="margin-top: 50px;">
	<div class="container">
		<spring:url value="/task/update" var="urlTaskUpdate"/>
		<form:form method="POST" action="task-manager-web/task/update" modelAttribute="task">
             <table class="table">
             <caption>Task form</caption>
                <tr>
                    <th><form:label path="id">Id</form:label></th>
                    <td><form:input path="id"/></td>
                </tr>
                <tr>
                    <th><form:label path="name">Name</form:label></th>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <th><form:label path="description">Descriptionr</form:label></th>
                    <td><form:input path="description"/></td>
                </tr>
                <tr>
                    <td rowspan="2"><input type="submit" value="Submit" class="btn btn-default"/></td>
                </tr>
            </table>
        </form:form>
	</div>
</div>