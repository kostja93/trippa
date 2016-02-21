<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSettingsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('settings', function(Blueprint $table) {
            $table->increments('id');
            $table->integer('user_id')->unsigned();

            $table->integer('price_id')->unsigned();
            $table->integer('equipment_id')->unsigned();
            $table->integer('atmosphere_id')->unsigned();

            $table->timestamps();

            $table->foreign('user_id')->references('id')->on('users');
            $table->foreign('price_id')->references('id')->on('price_id');
            $table->foreign('equipment_id')->references('id')->on('equipments');
            $table->foreign('atmosphere_id')->references('id')->on('atmospheres');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('settings');
    }
}
