from django.contrib.auth.models import User
from django.db import models
from django.utils import timezone
from django.utils.text import slugify

class Room(models.Model):
    name = models.CharField(max_length=255)
    slug = models.SlugField(unique=False)

    def __str__(self):
        return self.name

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        super(Room, self).save(*args, **kwargs)


def get_default_room():
    try:
        return Room.objects.get(slug=123)  
    except Room.DoesNotExist:
        return None

class Message(models.Model):
    content = models.TextField()
    timestamp = models.DateTimeField(default=timezone.now)
