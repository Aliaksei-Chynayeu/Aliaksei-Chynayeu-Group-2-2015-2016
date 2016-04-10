<%@ include file="../tag/tagInclude.jsp" %>

<div style="margin-top: 50px;">
	<div class="container">
		<spring:url value="/task/update" var="urlTaskUpdate"/>
		<form:form method="POST" action="task-manager-web/task/update" modelAttribute="task">
             <table class="table">
             <caption>Task View</caption>
                <tr>
                    <th>Id</th>
                    <td>{task.id}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td>${task.name}</td>
                </tr>
                <tr>
                    <th>Descriptionr</th>
                    <td>{task.description}</td>
                </tr>
                <tr>
                    <td rowspan="2"><input type="submit" value="Submit" class="btn btn-default"/></td>
                </tr>
            </table>
        </form:form>
	</div>
</div>