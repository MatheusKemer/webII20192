    //  Input Masks
$('#cpf').keyup(function() {
    var cpf = this.value;
    var num = cpf.replace(/[^\d]/g, ''); //remove todos os caracteres não numéricos
    var len = num.length; //guarda o número de digitos até o momento

  if(len <= 6){
    cpf = num.replace(/(\d{3})(\d{1,3})/g, '$1.$2');  
  }else if(len <= 9){
    cpf = num.replace(/(\d{3})(\d{3})(\d{1,3})/g, '$1.$2.$3');
  }else{
    cpf = num.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2})/g, "$1.$2.$3-$4");
  }
  this.value = cpf;
});
$('#cpf').blur(function() {
    var strCPF = this.value.replace(/\./g, '').replace(/\-/g,'');
    var Soma;
    var Resto;
    Soma = 0;
    if (strCPF === "00000000000") return false;
     
    for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;
   
    if ((Resto === 10) || (Resto === 11))  Resto = 0;
    if (Resto !== parseInt(strCPF.substring(9, 10)) ){
        this.value = '';
        $(this).css('border-color', 'red');
        return;
    }
   
    Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;
   
    if ((Resto === 10) || (Resto === 11))  Resto = 0;
    if (Resto !== parseInt(strCPF.substring(10, 11) ) ){
        this.value = '';
        $(this).css('border-color', 'red');
        return;
    }
    $(this).css('border-color', '#030303');
});
$('#email').blur(function() {
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!filter.test(this.value)) {
        this.value = '';
        $(this).css('border-color', 'red');
        return;
    }
    $(this).css('border-color', '#030303');
});
$('#housenumber').keyup(function() {
    var housenumber = this.value;
    var num = housenumber.replace(/[^\d]/g, '');
    this.value = num;
});
$('#cep').keyup(function() {
    var cep = this.value;
    var num = cep.replace(/[^\d]/g, ''); //remove todos os caracteres não numéricos
    var len = num.length; //guarda o número de digitos até o momento

    if(len <= 5){
        cep = num.replace(/(\d{2})(\d{1,3})/g, '$1.$2');  
    }else if(len <= 8){
        cep = num.replace(/(\d{2})(\d{3})(\d{1,3})/g, '$1.$2-$3');
    }
    this.value = cep;
});
$('#cep').blur(function() {
    var strCEP = this.value.replace(/\./g, '').replace(/\-/g,'');
    if (strCEP.length !== 8){
        this.value = '';
        $(this).css('border-color', 'red');
        return;
    }
    $(this).css('border-color', '#030303');
});
$('#phone').keyup(function() {
    var phone = this.value;
    var num = phone.replace(/[^\d]/g, ''); //remove todos os caracteres não numéricos
    var len = num.length; //guarda o número de digitos até o momento

    if(len <= 6){
        phone = num.replace(/(\d{2})(\d{1,4})/g, '($1)$2');  
    }else if(len <= 10){
        phone = num.replace(/(\d{2})(\d{4})(\d{1,4})/g, '($1)$2-$3');
    }else if(len === 11){
        phone = num.replace(/(\d{2})(\d{5})(\d{4})/g, '($1)$2-$3');
    }
    this.value = phone;
});
$('#phone').blur(function() {
    var strPhone = this.value.replace(/\(/g,'').replace(/\)/g,'').replace(/\./g, '').replace(/\-/g,'');
    if (strPhone.length !== 10 && strPhone.length !== 11){
        this.value = '';
        $(this).css('border-color', 'red');
        return;
    }
    $(this).css('border-color', '#030303');
});
$("#cpass").blur(function() {
    var cpass = this.value;
    var pass = $("#pass").val();
    if (cpass !== pass) {
        this.value = '';
        $(this).css('border-color', 'red');
        return;
    }
    $(this).css('border-color', '#030303');
});
$('#weightIn').keyup(function() {
    var weight = this.value;
    var num = weight.replace(/[^\d]/g, '');
    this.value = num;
});
    //  Modal Atendimentos
$('.questionView').click(function() {
    event.preventDefault();
    var id = $(this).parents('tr').find("td:first-child").html();
    $('#title').html($('#title'+id).html());
    $('#status').html($('#status'+id).html());
    $('#openDate').html($('#openDate'+id).html());
    $('#closeDate').html($('#closeDate'+id).html());
    $('#product').html($('#product'+id).html());
    $('#question').html($('#question'+id).html());
    $('#answer').html($('#answer'+id).html());
    $('#answer').html() !== '' && $('.questReply').addClass('hidden');
    $('#callID').val(id);
    $('.modal').removeClass('hidden');
    $('.modal-body').removeClass('hidden');
});
$('.modal-close').click(function() {
    event.preventDefault();
    $('.modal').addClass('hidden');
    $('.modal-body').addClass('hidden');
    $('#title').html('');
    $('#status').html('');
    $('#openDate').html('');
    $('#closeDate').html('');
    $('#product').html('');
    $('#question').html('');
    $('#answer').html('');
    $('.questReply').hasClass('hidden') && $('.questReply').removeClass('hidden');
    $('#callID').val('');
});
    //  Modal Categorias
$('.catView').click(function() {
    event.preventDefault();
    var id = $(this).parents('tr').find("td:first-child").html();
    $('#title').html($('#title'+id).html());
    $('.modal').removeClass('hidden');
    $('.modal-body').removeClass('hidden');
    $('#catID').val(id);
});
$('.cat-close').click(function() {
    event.preventDefault();
    $('.modal').addClass('hidden');
    $('.modal-body').addClass('hidden');
    $('#title').html('');
    $('#catID').val('');
});
$('.catEdit').click(function() {
    event.preventDefault();
    $('.catChange').each(function() {
        $(this).removeClass('hidden');
    });
    $(this).addClass('hidden');
    $('.cat-close').addClass('hidden');
    $('.catCancel').removeClass('hidden');
});
$('.catCancel').click(function() {
    event.preventDefault();
    $('.catChange').each(function() {
        $(this).addClass('hidden');
    });
    $(this).addClass('hidden');
    $('.cat-close').removeClass('hidden');
    $('.catEdit').removeClass('hidden');
});
    //  Modal Produtos
$('.prodView').click(function() {
    event.preventDefault();
    var id = $(this).parents('tr').find("td:first-child").html();
    $('#title').html($('#title'+id).html());
    $('#type').html($('#type'+id).html());
    $('#desc').html($('#desc'+id).html());
    $('#peso').html($('#peso'+id).html());
    $('.modal').removeClass('hidden');
    $('.modal-body').removeClass('hidden');
    $('#prodID').val(id);
});
$('.prod-close').click(function() {
    event.preventDefault();
    $('.modal').addClass('hidden');
    $('.modal-body').addClass('hidden');
    $('#title').html('');
    $('#type').html('');
    $('#desc').html('');
    $('#prodID').val('');
});
$('.prodEdit').click(function() {
    event.preventDefault();
    $('.prodChange').each(function() {
        $(this).removeClass('hidden');
    });
    $(this).addClass('hidden');
    $('.prod-close').addClass('hidden');
    $('.prodCancel').removeClass('hidden');
});
$('.prodCancel').click(function() {
    event.preventDefault();
    $('.prodChange').each(function() {
        $(this).addClass('hidden');
    });
    $(this).addClass('hidden');
    $('.prod-close').removeClass('hidden');
    $('.prodEdit').removeClass('hidden');
});    

    //  Modal Delete
$('.delete.prod').click(function() {
    event.preventDefault();
    $('.modal').removeClass('hidden');
    $('.modal-delete').removeClass('hidden');
    var objId = $(this).parents('tr').find("td:first-child").html();
    $('#delete-id').closest('form').attr('action', 'ProdutoServlet?action=remove&id=' + objId);
    $('#delete-id').val(objId);
});

$('.delete.cat').click(function() {
    event.preventDefault();
    $('.modal').removeClass('hidden');
    $('.modal-delete').removeClass('hidden');
    var objId = $(this).parents('tr').find("td:first-child").html();
    $('#delete-id').closest('form').attr('action', 'CategoriaServlet?action=remove&id=' + objId);
    $('#delete-id').val(objId);
});

$('.delete-cancel').click(function() {
    event.preventDefault();
    $('.modal').addClass('hidden');
    $('.modal-delete').addClass('hidden');
    $('#delete-id').val('');
});

//  Formulários
$('#date-report').click(function(){
    event.preventDefault();
    $('.modal').removeClass('hidden');
    $('.date-modal').removeClass('hidden');
});

$('#date-modal-close').click(function(){
    event.preventDefault();
    $('.modal').addClass('hidden');
    $('.date-modal').addClass('hidden');
});

$('#quest-report').click(function(){
    event.preventDefault();
    $('.modal').removeClass('hidden');
    $('.quest-modal').removeClass('hidden');
});

$('#quest-modal-close').click(function(){
    event.preventDefault();
    $('.modal').addClass('hidden');
    $('.quest-modal').addClass('hidden');
});

//  Tabelas
$('table').DataTable();

$('.questStat').each(function(){
    let status = $(this).html();
    if(status === 'Concluído'){
        $(this).parents('tr').css('color', 'white');
        $(this).parents('tr').css('background', 'green');
    }
    if(status === 'Aberto'){
        var date = $(this).siblings('.questDate').html();
        var fdate = date.substring(3,6) + date.substring(0,2) + date.substring(5, date.length);
        var whendate = new Date(fdate);
        var todate = new Date;
        var timeDiff = todate.getTime() - whendate.getTime();
        var dayDiff = timeDiff / (1000 * 3600 * 24);
        if (dayDiff >= 7){
            $(this).parents('tr').css('background', 'red');
        } else {
            $(this).parents('tr').css('background', 'yellow');
        }
    }
});

$('.userEdit').click(function(){
    var id = $(this).parents('tr').find("td:first-child").html();
    $('#edit-user-id').val(id);
    $('#edit-user-form').submit();
});

$('.datepicker').datepicker({
    format: "dd/mm/yyyy"
});