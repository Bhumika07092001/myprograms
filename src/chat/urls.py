from django.urls import path

from . import views
from django.contrib.auth import views as auth_views



urlpatterns = [
    path('', views.frontpage, name='frontpage'),
    path('signup/', views.signup, name='signup'),
    path('login/', auth_views.LoginView.as_view(template_name='login.html'), name='login'),
    path('logout/', auth_views.LogoutView.as_view(), name='logout'),
    path('rooms/', views.rooms, name='rooms'),
    path('room/<slug:room_slug>/', views.user_chat, name='room'),
    path('get_messages/', views.get_messages, name='get_messages'),
    path('send_message/', views.send_message, name='send_message'),
]
