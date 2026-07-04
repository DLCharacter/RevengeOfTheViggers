import os
from PIL import Image

def create_texture(path, size, color):
    img = Image.new('RGBA', size, color)
    os.makedirs(os.path.dirname(path), exist_ok=True)
    img.save(path)

base_path = 'src/main/resources/assets/savage_vegetables/textures/'

# Items (16x16)
items = {
    'mutagenic_fertilizer.png': (100, 200, 50, 255),
    'veggie_ammo.png': (150, 255, 100, 255),
    'firearm_ammo.png': (100, 100, 100, 255),
    'rifle.png': (50, 50, 50, 255),
    'veggie_blaster.png': (50, 150, 50, 255),
}

for name, color in items.items():
    create_texture(base_path + 'item/' + name, (16, 16), color)

# Blocks (16x16)
blocks = {
    'mutated_soil.png': (80, 50, 20, 255),
    'jerusalem_artichoke_crop.png': (150, 150, 100, 255),
    'rutabaga_crop.png': (200, 100, 200, 255),
    'celery_crop.png': (50, 200, 50, 255),
}

for name, color in blocks.items():
    create_texture(base_path + 'block/' + name, (16, 16), color)

# Entities (64x64)
entities = {
    'pumpkin.png': (220, 120, 20, 255),
    'tomato.png': (220, 20, 20, 255),
    'corn.png': (220, 220, 20, 255),
    'jerusalem_artichoke.png': (120, 100, 80, 255),
    'rutabaga.png': (180, 100, 150, 255),
    'celery.png': (100, 200, 80, 255),
    'hybrid.png': (100, 100, 100, 255),
    'eggplant.png': (100, 20, 150, 255),
    'zucchini.png': (50, 150, 80, 255),
}

for name, color in entities.items():
    create_texture(base_path + 'entity/' + name, (64, 64), color)

print("Textures generated.")
