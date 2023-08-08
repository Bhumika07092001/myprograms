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
        return Room.objects.get(slug=123)  # Replace 'default-room-slug' with the actual slug of the default room
    except Room.DoesNotExist:
        return None

class Message(models.Model):
    content = models.TextField()
    timestamp = models.DateTimeField(default=timezone.now)
    # room = models.ForeignKey(Room, related_name='messages', on_delete=models.CASCADE)


    



 
    # room = models.ForeignKey(Room, related_name='messages', on_delete=models.CASCADE)

    # def __str__(self):
    #     return f"Message in {self.room}: {self.content[:20]}"
    

    # room = models.ForeignKey(Room, related_name='messages', on_delete=models.CASCADE)
    # user = models.ForeignKey(User, related_name='messages', on_delete=models.CASCADE)
    # content = models.TextField()
    # date_added = models.DateTimeField(auto_now_add=True)

    # 
