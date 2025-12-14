<?php
require __DIR__ . '/auth.php';

use App\Http\Controllers\CategoryController;
use App\Http\Controllers\PreferenceController;
use App\Http\Controllers\ProjectController;
use App\Http\Controllers\ReminderController;
use App\Http\Controllers\TaskController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::middleware(['auth:sanctum'])->get('/user', function (Request $request) {
    return $request->user();
});

Route::middleware(['auth:sanctum'])->group(function() {
    Route::apiResource('tasks', TaskController::class);
});

Route::middleware(['auth:sanctum'])->group(function() {
    Route::apiResource('categories', CategoryController::class);
});

Route::middleware(['auth:sanctum'])->group(function() {
    Route::apiResource('projects', ProjectController::class);
});

Route::middleware(['auth:sanctum'])->group(function() {
    Route::apiResource('preference', PreferenceController::class);
});

Route::middleware(['auth:sanctum'])->group(function() {
    Route::apiResource('reminder', ReminderController::class);
});
