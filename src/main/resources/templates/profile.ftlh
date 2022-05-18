<#ftl encoding='UTF-8'>
<#include "base.ftlh">
<#macro title>Profile</#macro>
<#macro main>
    <div class="container">
        <div class="row my-5">
            <div class="col-md-4 col-sm-12">
                <div class="user__info">
                    <img class="rounded-circle" width="192" height="192" src="${user.imageMetadata.url}" alt="image">
                    <div class="user__info__name">
                        <p class="h3">${user.firstName} ${user.lastName}</p>
                        <p>@${user.username}</p>
                    </div>
                    <#if self??>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#profileImageModal">
                            Изменить фото профиля
                        </button>
                    </#if>
                </div>
            </div>
            <div class="col-md-8 col-sm-12 align-self-center">
                <div class="user__info__description">
                    <p class="h3">Bio</p>
                    <p>${user.username}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="reviews__header">
                    <p>Рецензии пользователя</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="container-fluid reviews">
                    <#list user.reviews as review>
                        <div class="row review__item">
                            <div class="col-md-2 align-self-center">
                                <a href="/movies/${review.movie.id}" class="review__link">
                                    <p class="movie__name">
                                        ${review.movie.name}
                                    </p>
                                </a>
                            </div>
                            <div class="col-md-2 offset-md-3 align-self-center">
                                <a href="#" class="review__selector" id="id=${review.id}">
                                    <p class="movie__name" id="id=${review.id}">
                                        ${review.rating}/100
                                    </p>
                                </a>
                            </div>
                            <div class="col-md-2 offset-md-3 align-self-center">
                                <p class="movie__name">
                                    ${review.createDate}
                                </p>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <!-- Review view modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Рецензия пользователя ${user.username}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body modal__review__body">
                    <div class="review__header">
                        <p class="h4">Рецензия на <a href="" id="modal-review-movieName" class="link__grey">movieName</a></p>
                    </div>
                    <div class="review__body">
                        <p>Оценка: <span id="modal-review-rating" class="review__rating__span"></span></p>
                        <p>Рецензия:</p>
                        <p id="modal-review-text"></p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Profile image modal -->
    <div class="modal fade" id="profileImageModal" tabindex="-1" aria-labelledby="profileImageModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="profileImageModalLabel">Изменить фото профиля</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body modal__review__body">
                    <form action="/me/image" method="post" enctype="multipart/form-data">
                        <label for="image" class="form-label">Изображение в формате jpg,png размером не более 2 МБ</label>
                        <input id="image" class="form-control" type="file" name="image">
                        <br>
                        <input class="btn text-center" type="submit" value="Изменить фото профиля">
                    </form>
                </div>
            </div>
        </div>
    </div>
</#macro>