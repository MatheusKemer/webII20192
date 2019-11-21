<%-- 
    Document   : clienteVisualizar
    Created on : 02/10/2019, 21:57:57
    Author     : matheus
--%>

<%@page import="beans.Cliente"%>
<%@page import="beans.LoginBean"%>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <jsp:useBean id="titleLabel" class="String" scope="request" />
    <jsp:useBean id="action" class="String" scope="request" />
    <jsp:useBean id="editing" class="Boolean" scope="request" />
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${titleLabel}" /></title>
        <link rel="stylesheet" href="css/bootstrap.css" />
    </head>
    <body>
        <c:import url="/header.jsp" />
        <c:set var="cliente" value="${sessionScope.Cliente}" scope="session" />
        
        <div class="container">
            <div class="col-xs-12 col-sm-6 col-sm-offset-3">
                <h2><c:out value="${titleLabel}" /></h2>
                <form action="<c:out value="${action}" />" method="post">
                    <c:choose>
                        <c:when test="${editing == true}">
                            <input type="hidden" name="id" value="<c:out value="${cliente.id}" />"/>
                        </c:when>
                    </c:choose>
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" name="nome" class="form-control" maxlength="100" value="<c:out value="${cliente.nome}" />" required>
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" class="form-control" maxlength="100" value="<c:out value="${cliente.email}" />" required>
                    </div>

                    <div class="form-group">
                        <label>CPF</label>
                        <input type="text" name="cpf" class="form-control" maxlength="11" value="<c:out value="${cliente.cpf}" />" required>
                    </div>

                    <div class="form-group">
                        <label>Data</label>
                        <input type="date" name="data" class="form-control" value="<c:out value="${cliente.data}" />" required>
                    </div>

                    <div class="form-group">
                        <label>CEP</label>
                        <input type="number" name="cep" maxlength="8" class="form-control" value="<c:out value="${cliente.cep}" />" required>
                    </div>

                    <div class="form-group">
                        <label>Rua</label>
                        <input type="text" name="rua" class="form-control" maxlength="100" value="<c:out value="${cliente.rua}" />" required>
                    </div>

                    <div class="form-group">
                        <label>Número</label>
                        <input type="numver" name="numero" class="form-control" value="<c:out value="${cliente.numero}" />" required>
                    </div>

                    <div class="form-group">
                        <label>UF</label>
                        <select class="form-control" id="estado" name="estadoId">
                            <c:forEach items="${estados}" var="estado" >
                                <option value="<c:out value="${estado.id}"/>" <c:if test="${estado.id == cliente.cidade.estado.id}"><c:out value="selected=true" /></c:if> >
                                    <c:out value="${estado.uf}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Cidade</label>
                        <select class="form-control" id="cidade" required name="cidadeId">
                            <c:if test="${editing == true}">
                                <c:forEach items="${cidades}" var="cidade" >
                                    <option value="<c:out value="${cidade.id}"/>" <c:if test="${cidade.id == cliente.cidade.id}"><c:out value="selected=true" /></c:if>>
                                        <c:out value="${cidade.nome}"/>
                                    </option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <br />

                    <div class="">
                        <a href="ClientesServlet" class="btn btn-danger pull-left"> Voltar </a>
                        <button type="submit" class="btn btn-success pull-right"> 
                            <c:choose>
                                <c:when test="${editing == true}">
                                    <c:out value="Atualizar" />
                                </c:when>
                                <c:otherwise>
                                    <c:out value="Criar" />
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </div>
                </form>
            </div>        
        </div>
    </body>
</html>
<script src="jquery.min.js"></script>
<script type="text/javascript" >
    $(document).ready(function() {
        $("#estado").change(function() {
            getCidades();
        });
    });

    function getCidades(){
        var estadoId = $("#estado").val();
        var url = "AJAXServlet";
        $.ajax({
            url : url, // URL da sua Servlet
            data : {
                estadoId : estadoId
            }, // Parâmetro passado para a Servlet
            dataType : 'json',
            success : function(data) {
                // Se sucesso, limpa e preenche a combo de cidade
                // alert(JSON.stringify(data));
                $("#cidade").empty();
                $.each(data, function(i, obj) {
                    $("#cidade").append('<option value=' + obj.id + '>' + obj.nome + '</option>');

                });
                $("#cidade").prop( "disabled", false );
            },
            error : function(request, textStatus, errorThrown) {
                alert(request.status + ', Error: ' + request.statusText);
                 // Erro
            }
        });
    }
</script>
