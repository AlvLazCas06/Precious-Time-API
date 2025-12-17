<?php
require __DIR__ . '/auth.php';

use App\Http\Controllers\CategoryController;
use App\Http\Controllers\PreferenceController;
use App\Http\Controllers\ProjectController;
use App\Http\Controllers\ReminderController;
use App\Http\Controllers\TaskController;
use App\Http\Controllers\TaskUserController;
use App\Http\Controllers\UserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::middleware(['auth:sanctum'])->get('/user', function (Request $request) {
    return $request->user();
});

Route::middleware(['auth:sanctum'])->group(function() {
    Route::apiResource('tasks', TaskController::class);
    Route::apiResource('categories', CategoryController::class);
    Route::apiResource('projects', ProjectController::class);
    Route::apiResource('preference', PreferenceController::class);
    Route::apiResource('reminder', ReminderController::class);
    Route::apiResource('task-user', TaskUserController::class);
    Route::apiResource('users', UserController::class);
});
