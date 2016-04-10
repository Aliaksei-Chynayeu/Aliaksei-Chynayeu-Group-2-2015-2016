<%@ include file="../tag/tagInclude.jsp" %>

<div style="margin-top: 50px;">
	<div class="container">
		<table class="table">
			<caption>Task List</caption>
			<tr>
				<th>ID</th>				
				<th>Name</th>
				<th>Description</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="task" items="${taskList}">
				<tr>
					<td><c:out value="${task.id}" /></td>
					<td><c:out value="${task.name}" /></td>
					<td><c:out value="${task.description}" /></td>
					<td>
						<spring:url value="/task" var="urlTaskDelete"/>
						<form:form method="DELETE" action="${urlTaskDelete}/${task.id}">
							<button class="btn btn-default">delete</button>
						</form:form>
					</td>
					<td>Edit</td>
				</tr>
			</c:forEach>
		</table>
		<div class="container">
			<spring:url value="/task/create" var="urlTaskCreate"/>
			<a href="${urlTaskCreate}" class="btn btn-default">Create New Task</a>
		</div>
	</div>
</div>