# Generated by Django 4.2.3 on 2023-07-26 10:10

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('chat', '0005_remove_message_room_remove_message_user'),
    ]

    operations = [
        migrations.AddField(
            model_name='room',
            name='slug',
            field=models.SlugField(default=123, unique=True),
            preserve_default=False,
        ),
    ]