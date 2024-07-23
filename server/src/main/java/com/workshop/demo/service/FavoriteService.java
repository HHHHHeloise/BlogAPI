package com.workshop.demo.service;

import java.util.List;

import com.workshop.demo.model.Favorite;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.FavoriteRequest;
import com.workshop.demo.security.UserPrincipal;

public interface FavoriteService {

    Favorite addFavorite(FavoriteRequest favoriteRequest, UserPrincipal userPrincipal);

    ApiResponse removeFavorite(FavoriteRequest favoriteRequest, UserPrincipal userPrincipal);

    List<Favorite> searchByUserId(Long userId, UserPrincipal userPrincipal);
}
