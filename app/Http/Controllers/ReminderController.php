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
        return Reminder::where("user_id", auth()->user()->id)->get();
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
            'title' => ['required', 'string', 'max:255'],
            'message' => ['nullable', 'string']
        ]);

        $reminder = Reminder::create([
            'user_id' => $request->user_id,
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
    /**
     * Handle an incoming registration request.
     *
     * @throws \Illuminate\Validation\ValidationException
     */
    public function edit(Reminder $reminder)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    /**
     * Handle an incoming registration request.
     *
     * @throws \Illuminate\Validation\ValidationException
     */
    public function update(Request $request, Reminder $reminder)
    {
        $reminder->update($request->all());
        return response()->json($reminder, 200);
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
