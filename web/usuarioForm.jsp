<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2><c:out value="${titleLabel}" /></h2>
<form action="<c:out value="${action}" />" method="post">
    <c:choose>
        <c:when test="${editing == true}">
            <input type="hidden" name="id" value="<c:out value="${cliente.id}" />"/>
        </c:when>
    </c:choose>
            
    <div class="form-group">
        <label>Nome</label>
        <input type="text" name="nome" maxlength="100" value="<c:out value="${cliente.nome}" />" required>
    </div>
            
    <div class="form-group">
        <label>Email</label>
        <input type="text" name="email" class="form-control" maxlength="100" value="<c:out value="${cliente.email}" />" value="<c:out value="${cliente.cpf}" />" <c:if test="${editing}"><c:out value="disabled" /></c:if> required>
    </div>

    <div class="form-group">
        <label>CPF</label>
        <input id="cpf" type="text" name="cpf" class="form-control" maxlength="11" value="<c:out value="${cliente.cpf}" />" <c:if test="${editing}"><c:out value="disabled" /></c:if> required>
    </div>

    <div class="form-group">
        <label>Data</label>
        <input type="date" name="data" class="form-control" value="<fmt:formatDate value="${cliente.data}" pattern="dd/MM/yyyy" />" required>
    </div>

    <div class="form-group">
        <label>CEP</label>
        <input id="cep" type="text" name="cep" maxlength="8" class="form-control" value="<c:out value="${cliente.cep}" />" required>
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
    
    <div class="form-group">
        <label>Telefone</label>
        <input name="telefone" type="phone" id="phone" placeholder="Telefone" value="<c:out value="${usuario.telefone}" />">
    </div>
    <div class="form-group">
        <label>Senha</label>
        <input name="senha" type="password" id="pass" placeholder="Senha">
    </div>
    <div class="form-group">
        <label>Confirmação Senha</label>
        <input name="confirmacao_senha" type="password" id="cpass" placeholder="Confirmar Senha">
    </div>
        
    <br />

    <div class="">
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
