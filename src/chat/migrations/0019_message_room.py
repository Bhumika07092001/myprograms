# Generated by Django 4.2.3 on 2023-07-30 16:44

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('chat', '0018_remove_message_room'),
    ]

    operations = [
        migrations.AddField(
            model_name='message',
            name='room',
            field=models.ForeignKey(default=123, on_delete=django.db.models.deletion.CASCADE, related_name='messages', to='chat.room'),
            preserve_default=False,
        ),
    ]