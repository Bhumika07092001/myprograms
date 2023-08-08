from django.contrib.auth import login
from django.shortcuts import render, redirect
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import json
from .forms import SignupForm
from django.contrib.auth.decorators import login_required
from django.shortcuts import render,get_object_or_404

from .models import Room, Message

# @login_required
def rooms(request):
    all_rooms = Room.objects.all()

    return render(request, 'rooms.html', {'rooms': all_rooms})


def user_chat(request,room_slug):
    room = get_object_or_404(Room, slug=room_slug)
    # messages = Message.objects.filter(room=room).order_by('-timestamp')

    return render(request, 'user_chat.html',{'room': room})

def get_messages(request):
    messages = Message.objects.all().values('content')
    return JsonResponse(list(messages), safe=False)

@csrf_exempt
def send_message(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        content = data.get('content')
        if content:
            Message.objects.create(content=content)
            return JsonResponse({'status': 'success'})
    return JsonResponse({'status': 'error'}, status=400)


def frontpage(request):
    return render(request, 'front_page.html')

def signup(request):
    if request.method == 'POST':
        form = SignupForm(request.POST)

        if form.is_valid():
            user = form.save()

            login(request, user)

            return redirect('frontpage')
    else:
        form = SignupForm()
    
    return render(request, 'signup.html', {'form': form})