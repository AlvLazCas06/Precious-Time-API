<?php

namespace App\Http\Controllers;

use App\Models\Reminder;
use Illuminate\Http\Request;

class ReminderController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
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
    public function store(Request $request)
    {
        $request->validate([
            'task_id' => ['nullable', 'integer'],
            'project_id' => ['nullable', 'integer'],
            'title' => ['required', 'string', 'max:255'],
            'message' => ['nullable', 'string']
        ]);

        $reminder = Reminder::create([
            'task_id' => $request->task_id,
            'project_id' => $request->project_id,
            'user_id' => auth()->id(),
            'title' => $request->title,
            'message' => $request->message
        ]);
        return response()->json($reminder, 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(Reminder $reminder)
    {
        return $reminder;
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Reminder $reminder)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Reminder $reminder)
    {
        $request->validate([
            'task_id' => ['nullable', 'integer'],
            'project_id' => ['nullable', 'integer'],
            'title' => ['required', 'string', 'max:255'],
            'message' => ['nullable', 'string']
        ]);

        $reminder->update($request->all());
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Reminder $reminder)
    {
        $reminder->delete();
        return response()->json([], 204);
    }
}
