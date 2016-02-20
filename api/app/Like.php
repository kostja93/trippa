<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Like extends Model
{
    protected $fillable = ['location_id', 'user', 'like'];

    public function location() {
        return $this->belongsTo(Location::class);
    }

    public function user() {
        return $this->belongsTo(User::class);
    }
}
