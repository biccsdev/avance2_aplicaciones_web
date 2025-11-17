<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %> <%-- Importante para formatear números y fechas --%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <%-- 1. Necesitarás crear este archivo CSS para los estilos de esta página --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pedidosAdmin.css"> 
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Gestionar Pedidos</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="div-main">
        <%-- Filtros basados en la imagen --%>
        <div class="sidebar-filter">
            <h1 class="title">Gestionar Pedidos</h1>
            <label class="filter-label" for="cliente-filtro">Cliente:</label>
            <input id="cliente-filtro" class="input-filter" type="text" placeholder="Buscar por cliente...">
            
            <label class="filter-label" for="fecha-filtro">Fecha:</label>
            <input id="fecha-filtro" class="input-filter" type="date">

            <label class="filter-label" for="estado-filtro">Estado:</label>
            <select id="estado-filtro" class="input-filter">
                <option value="">Todos</option>
                <option value="PENDIENTE">Pendiente</option>
                <option value="ENVIADO">Enviado</option>
                <option value="ENTREGADO">Entregado</option>
                <option value="CANCELADO">Cancelado</option>
            </select>
        </div>

        <div class="main-content"> <%-- Cambié 'main-users' por 'main-content' --%>

            <%-- Mensajes de error o éxito --%>
            <c:if test="${not empty errorMessage}">
                <h4 style="color: red; text-align: center;">${errorMessage}</h4>
            </c:if>
            <c:if test="${not empty successMessage}">
                <h4 style="color: green; text-align: center;">${successMessage}</h4>
            </c:if>

            <%-- 2. Iteramos sobre la lista de pedidos enviada desde el servlet --%>
            <c:forEach var="pedido" items="${listaPedidos}">
                <div class="pedido-item"> <%-- Tendrás que estilizar .pedido-item en tu CSS --%>
                    
                    <div class="pedido-icon-container">
                        <%-- Puedes cambiar este icono por uno de "pedido" --%>
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de pedido">
                    </div>

                    <div class="pedido-data">
                        <%-- 3. AJUSTA ESTAS PROPIEDADES a como se llamen en tu PedidoDTO --%>
                        <h4>ID Pedido: ${pedido.id}</h4>
                        <%-- Asumo que el DTO de Pedido tiene un objeto ClienteDTO dentro --%>
                        <h6>Cliente: ${pedido.cliente.nombres} ${pedido.cliente.apellidos}</h6> 
                        
                        <%-- Formateo de fecha (requiere que pedido.fecha sea un objeto Date) --%>
                        <fmt:formatDate value="${pedido.fecha}" pattern="dd/MM/yyyy" var="fechaFormateada"/>
                        <span>Fecha: ${fechaFormateada}</span>
                        
                        <%-- Formateo de moneda --%>
                        <fmt:formatNumber value="${pedido.total}" type="currency" currencySymbol="$" var="totalFormateado"/>
                        <span>Total: ${totalFormateado}</span>

                        <div class="pedido-status">
                            <%-- Clase dinámica para el color del estado (ej. .estado-pendiente) --%>
                            <span class="status-dot estado-${pedido.estado.toLowerCase()}"></span>
                            <span>Estado: ${pedido.estado}</span>
                        </div>
                    </div>

                    <div class="pedido-actions">
                        <%-- Acción 1: Ver Detalles (como enlace) --%>
                        <a href="${pageContext.request.contextPath}/admin/pedidos/detalle?id=${pedido.id}" class="btn-detalle">
                            Ver Detalles
                        </a>

                        <form class="pedido-action-form" method="POST" action="${pageContext.request.contextPath}/admin/pedidos/gestionar">
                            <input type="hidden" name="action" value="cambiarEstado">
                            <input type="hidden" name="pedidoId" value="${pedido.id}">
                            
                            <select name="nuevoEstado">
                                <option value="PENDIENTE" ${pedido.estadoPedido == 'PENDIENTE' ? 'selected' : ''}>Pendiente</option>
                                <option value="ENVIADO" ${pedido.estadoPedido == 'ENVIADO' ? 'selected' : ''}>Enviado</option>
                                <option value="ENTREGADO" ${pedido.estadoPedido == 'ENTREGADO' ? 'selected' : ''}>Entregado</option>
                                <option value="CANCELADO" ${pedido.estadoPedido == 'CANCELADO' ? 'selected' : ''}>Cancelado</option>
                            </select>
                            <button type="submit" class="btn-actualizar">Actualizar</button>
                        </form>
                    </div>
                </div>
            </c:forEach>

            <%-- Mensaje si no hay pedidos --%>
            <c:if test="${empty listaPedidos}">
                <div class="pedido-item">
                    <div class="pedido-data">
                        <h4>(No se encontraron pedidos)</h4>
                    </div>
                </div>
            </c:if>

        </div>
    </main>
</body>
</html>

