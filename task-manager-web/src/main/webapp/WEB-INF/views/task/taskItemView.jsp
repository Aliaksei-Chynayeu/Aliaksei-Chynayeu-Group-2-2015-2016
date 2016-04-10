<%@ include file="../tag/tagInclude.jsp" %>

<div style="margin-top: 50px;">
	<div class="container">
		<spring:url value="/task/update" var="urlTaskUpdate"/>
             <table class="table">
             <caption>Task View</caption>
                <tr>
                    <th>Id</th>
                    <td>${task.id}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td>${task.name}</td>
                </tr>
                <tr>
                    <th>Descriptionr</th>
                    <td>${task.description}</td>
                </tr>
                <tr>
                    <td>						
                    	<spring:url value="/task" var="urlTaskDelete"/>
						<form:form method="DELETE" action="${urlTaskDelete}/${task.id}">
							<button class="btn btn-default">delete</button>
						</form:form>
					</td>
                    <td>
                    	<spring:url value="/task/list" var="urlTaskList"/>
						<a href="${urlTaskList}" class="btn btn-default">Ok</a>
					</td>
                </tr>
            </table>
	</div>
</div>