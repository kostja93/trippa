<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
    return view('welcome');
});

//User
Route::post('user','UserController@store');
Route::get('user/{id}','UserController@show');
Route::post('user/like', 'LikeController@store');

//Locations
Route::get('location', 'LocationController@index');
Route::post('location', 'LocationController@store');
Route::get('location/{id}', 'LocationController@show');
Route::get('location/{lat}/{lon}/{radius?}', 'LocationController@locations');
Route::get('hotels/{lat}/{lon}', 'LocationController@hotels');

//Values
Route::get('values', 'ValuesController@index');
