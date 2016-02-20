<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateRatingsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('prices', function (Blueprint $table) {
            $table->increments('id');
            $table->string('desc');
            $table->timestamps();
        });
        Schema::create('equipments', function (Blueprint $table) {
            $table->increments('id');
            $table->string('desc');
            $table->timestamps();
        });
        Schema::create('atmospheres', function (Blueprint $table) {
            $table->increments('id');
            $table->string('desc');
            $table->timestamps();
        });

        Schema::create('ratings', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('location_id')->unsigned();
            $table->integer('user_id')->unsigned();
            $table->integer('price_id')->unsigned();
            $table->integer('equipment_id')->unsigned();
            $table->integer('atmosphere_id')->unsigned();
            $table->double('stars')->unsigned();
            $table->timestamps();

            $table->foreign('location_id')->references('id')->on('locations');
            $table->foreign('user_id')->references('id')->on('users');
            $table->foreign('price_id')->references('id')->on('prices');
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
        Schema::drop('prices');
        Schema::drop('equipments');
        Schema::drop('atmospheres');
        Schema::drop('ratings');
    }
}
