<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="blackshape">
    <c:choose>
        <c:when test="${sessionScope.role.equals('admin')}">
            <h3>Panel Administracyjny</h3>
            <div id="admin_menu">
                <form method="post" action="/statistic">
                    <button class="button-ms" type="submit"
                            name="menu_button" value=""><img src="../svg/group.svg" width="34" height="34"/><span
                            class="menu_text">lista użytkowników</span>
                    </button>
                </form>
                <form method="post" action="/rest-api-raport">
                    <button class="button-ms" type="submit"
                            name="menu_button" value=""><img src="../svg/pie-chart-1.svg" width="34" height="34"/><span
                            class="menu_text">raport aktywności</span>
                    </button>
                </form>
                <form method="post" action="/bus-promotion">
                    <button class="button-ms" type="submit"
                            name="menu_button" value=""><img src="../svg/tag-1.svg" width="34" height="34"/><span
                            class="menu_text">promocje</span>
                    </button>
                </form>

                <form method="post" action="/chartActivityDependingOnAge">
                    <button class="button-ms" type="submit"
                            name="menu_button" value=""><img src="../svg/pie-chart.svg" width="34" height="34"/><span
                            class="menu_text">wykres aktywności ze wzglęgu wiek</span>
                    </button>
                </form>
                <form method="post" action="/chartActivityDependingOnGender">
                    <button class="button-ms" type="submit"
                            name="menu_button" value=""><img src="../svg/bar-chart.svg" width="34" height="34"/><span
                            class="menu_text">wykres aktywność ze wzglęgu płeć</span>
                    </button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <h3>Menu</h3>
            <div id="admin_menu">
                <form method="post" action="/time-table">
                    <button class="button-ms" type="submit"
                            name="menu_button" value=""><img src="../svg/ic_forward_white_24px.svg" width="34" height="34"/><span
                            class="menu_text">Rozkład jazdy</span>
                    </button>
                </form>
                <form method="post" action="/events">
                    <button class="button-ms" type="submit"
                            name="menu_button" value=""><img src="../svg/ic_forward_white_24px.svg" width="34" height="34"/><span
                            class="menu_text">kalendarz</span>
                    </button>
                </form>
                <form method="post" action="/about">
                    <button class="button-ms" type="submit"
                            name="menu_button" value=""><img src="../svg/ic_forward_white_24px.svg" width="34" height="34"/><span
                            class="menu_text">o nas</span>
                    </button>
                </form>

            </div>
        </c:otherwise>
    </c:choose>
</div>