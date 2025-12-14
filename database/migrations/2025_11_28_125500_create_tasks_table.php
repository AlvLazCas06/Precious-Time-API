<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('tasks', function (Blueprint $table) {
            $table->id();
            $table->foreignId('category_id')->constrained('categories', 'id')->cascadeOnDelete()->default(1);
            $table->string('title');
            $table->text('description')->nullable();
            $table->enum('status', ['pendiente', 'en_proceso', 'completado'])->default('pendiente');
            $table->enum('priority',['baja', 'media', 'alta'])->default('baja');
            $table->boolean('done')->default(false);
            $table->datetime('completed_at')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('tasks');
    }
};
