<#ftl encoding='UTF-8'>
<#include "base.ftlh">
<#macro title>Genres</#macro>
<#macro main>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <br>
                <h2>Жанры кино:</h2>
                <ul class="list-unstyled">
                    <#list genres as genre>
                        <li>
                            <a href="/genres/${genre.id}" class="link__grey">
                                <strong>${genre.name}</strong>
                            </a>
                        </li>
                    </#list>
                </ul>
        </div>
    </div>
</#macro>