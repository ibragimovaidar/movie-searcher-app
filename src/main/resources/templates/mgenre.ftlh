<#ftl encoding='UTF-8'>
<#include "base.ftlh">
<#macro title>Movies</#macro>
<#macro main>
    <div class="container">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <br>
            <h2>${genre.name}</h2>
            <ul class="list-unstyled">
                <#list genre.movies as movie>
                    <li>
                        <a href="/movies/${movie.id}" class="link__grey">
                            <strong>${movie.name}</strong>
                        </a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</#macro>