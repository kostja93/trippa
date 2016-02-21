<?php

namespace App\Http\Controllers;

use App\GoogleLocationApi;
use App\Location;
use App\Setting;
use App\User;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;

class UserController extends Controller
{
    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $user = new User();

        $user->name = $request->get('name');

        try {
            $user->save();

//            $this->saveUserSetting($user);
            return $user;
        } catch(\Exception $e) {
            return $this->errorMsg($e->getMessage());
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        try {
            $user = User::find($id);
            if ($user) {
                /** @var User $user */
                $user = $user->first();
                return $user;
            } else
                return [];
        }catch (\Exception $e) {
            return $this->errorMsg();
        }
    }

    private function saveUserSetting($user, $price, $equipment, $atmosphere) {
        $setting = new Setting();
        $setting->price_id = $price;
        $setting->equipment_id = $equipment;
        $setting->atmosphere_id = $atmosphere;
        $setting->user_id = $user->id;

        $setting->save();
    }
}
