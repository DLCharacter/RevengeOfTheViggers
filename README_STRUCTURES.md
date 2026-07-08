# Инструкция по добавлению кастомных структур (NBT)

1. Зайдите в Minecraft, постройте вашу структуру и сохраните её с помощью ванильного Structural Block (Структурный блок). Назовите её `lab_base`.
2. Найдите сохраненный файл `lab_base.nbt` в папке вашего мира: `.minecraft/saves/<Ваш_Мир>/generated/minecraft/structures/lab_base.nbt`
3. Скопируйте этот файл в исходники мода по пути: `src/main/resources/data/savage_vegetables/structures/lab_base.nbt`
4. Если вы хотите добавить другие структуры (например, `military_base`), просто скопируйте три JSON-файла (`lab_base.json`) из папок:
   - `src/main/resources/data/savage_vegetables/worldgen/template_pool/`
   - `src/main/resources/data/savage_vegetables/worldgen/structure/`
   - `src/main/resources/data/savage_vegetables/worldgen/structure_set/`
   Переименуйте их в `military_base.json` и внутри файлов замените все упоминания `lab_base` на `military_base`.
5. Соберите мод, и ваши структуры начнут генерироваться в мире автоматически!
