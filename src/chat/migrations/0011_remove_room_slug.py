# Generated by Django 4.2.3 on 2023-07-26 10:41

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chat', '0010_alter_room_slug'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='room',
            name='slug',
        ),
    ]
