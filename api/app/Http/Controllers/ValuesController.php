<?php

namespace App\Http\Controllers;

use App\Atmosphere;
use App\Equipment;
use App\Price;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;

class ValuesController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $prices = Price::all();
        $equipments = Equipment::all();
        $atmospheres = Atmosphere::all();

        return [
            'prices' => $prices,
            'equipments' => $equipments,
            'atmospheres' => $atmospheres
        ];
    }
}
