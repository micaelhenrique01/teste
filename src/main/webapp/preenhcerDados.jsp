<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="itens.json"></script>


<title>Preencher/Remover JSON</title>
<style type="text/css">
.form{
    width: 550px;
    margin-left: 50px;
    margin-top: 50px;
}
</style>

<body>
	<div class="form">
		<form action="adicionar" method="get">
		<h1>Adicionar</h1>
			<div class="form-group">
				<label for="nomeComputador">Nome computador</label> <input
					type="text" class="form-control" name="nomeComputador">
			</div>
			<div class="form-group">
				<label for="nomeSensor">Nome sensor</label> <input type="text"
					class="form-control" name="nomeSensor">
			</div>
			<div class="form-group">
				<label for="tipoSensor">Tipo sensor</label> <input type="text"
					class="form-control" name="tipoSensor">
			</div>
			<div class="form-group">
				<label for="contexto">Contexto</label> <input type="text"
					class="form-control" name="contexto">
			</div>
			<div class="form-group">
				<label for="localLab">Local laboratorio</label> <input type="text"
					class="form-control" name="localLab">
			</div>
			<div class="form-group">
				<label for="cidade">Cidade</label> <input type="text"
					class="form-control" name="cidade">
			</div>
			<div class="form-group">
				<label for="qtdVariaveis">Número de variavies</label> <input
					type="text" class="form-control" name="qtdVariaveis">
			</div>

			<button type="submit" class="btn btn-primary">Adicionar</button>
		</form>
		<form action="remover" method="get">
		<h1>Remover</h1>
			<div class="form-group">
				<label for="nomeSensor">Nome sensor</label> <input type="text"
					class="form-control" name="nomeSensor">
			</div> 
			<button type="submit" class="btn btn-primary">Remover</button>
		</form>
		<h3>${mensagem}</h3>
	</div>
</body>
</html>