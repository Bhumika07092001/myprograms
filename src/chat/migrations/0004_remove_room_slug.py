# Generated by Django 4.2.3 on 2023-07-25 12:55

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chat', '0003_message_timestamp'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='room',
            name='slug',
        ),
    ]
