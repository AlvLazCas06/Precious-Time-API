<?php

namespace App\Http\Controllers;

use App\Models\TaskUser;
use Illuminate\Http\Request;

class TaskUserController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return TaskUser::where('user_id', auth()->id())->get();
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    /**
     * Handle an incoming registration request.
     *
     * @throws \Illuminate\Validation\ValidationException
     */
    public function store(Request $request)
    {
        $request->validate([
            'task_id' => ['required', 'integer']
        ]);
        $taskUser = TaskUser::create($request->all());
        return response()->json($taskUser, 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(TaskUser $task_User)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(TaskUser $task_User)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, TaskUser $task_User)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(TaskUser $task_User)
    {
        $task_User->delete();
        return response()->json([], 204);
    }
}
