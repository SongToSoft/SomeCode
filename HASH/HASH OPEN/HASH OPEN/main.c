#include <stdio.h>
#include <stdlib.h>

typedef int t_htbl_key;
typedef int t_htbl_val;
typedef size_t (*t_htbl_hash)(t_htbl_key);

int hash_func(t_htbl_key key)
{
    int m=20000;
    return (key % m);
}

typedef struct t_htbl_open_rec
{
    char flag;
    t_htbl_key key;
    t_htbl_val val;
} t_htbl_open_rec;

typedef struct{
    size_t len;//длина
    size_t num;//общее кол во записей
    t_htbl_open_rec *table;
    t_htbl_hash hash;
}t_htbl_open;

t_htbl_open* htbl_open_new (size_t len,t_htbl_hash hash)
{
    int i;
    t_htbl_open *tbl;
    tbl=(t_htbl_open*)(malloc(sizeof(t_htbl_open)));
    tbl->table=(t_htbl_open_rec*)(malloc(sizeof(t_htbl_open_rec)*len));
    tbl->len=len;
    tbl->num=0;
    tbl->hash = hash;
    for(i=0;i<len;++i)
    {
        (tbl->table[i]).flag=0;
    }
    return tbl;
}

//Удаление таблицы с открытой адрисацией
void htbl_open_del(t_htbl_open *tbl)
{
    int i;
    free(tbl->table);
    free(tbl);
}

//Есть ли элемент в таблице
t_htbl_val *htbl_open_get_rec(t_htbl_key key, t_htbl_open *tbl)
{
    int i=hash_func(key);
    int j=i;
    while (1)
    {
        if (tbl->table[i].flag==0)
        {
            return NULL;
        }
        if (i==tbl->len)
        {
            i=0;
        }
        if (((tbl->table[i]).flag==1) && ((tbl->table[i]).key==key))
        {
            ++((tbl->table[i]).val);
            return &((tbl->table[i]).val);
        }
        else
        {
            ++i;
        }
        if (i==j)
        {
            return NULL;
        }
    }
}

//Добавление элемента с открытой адрисацией
t_htbl_val *htbl_open_add_rec(t_htbl_key key, t_htbl_open *tbl)
{
    int i=hash_func(key);
    if (htbl_open_get_rec (key,tbl)==NULL)
    {
        while (1)
        {
            if (i==tbl->len)
            {
                return NULL;
            }
            if (((tbl->table[i]).flag==0) || ((tbl->table[i]).flag==-1))
            {
                ++(tbl->num);
                (tbl->table[i]).flag= 1;
                (tbl->table[i]).key= key;
                (tbl->table[i]).val = 1;
                return &((tbl->table[i]).val);
            }
            else
            {
                ++i;
            }
        }
    }
    return &((tbl->table[i]).val);
}

//Удаление элемента с открытой адрисацией
void htbl_open_del_rec(t_htbl_key key, t_htbl_open *tbl)
{
    int i=tbl->hash(key);
    int j=i;
    while (1)
    {
        if ((tbl->table[i]).flag==0)
        {
            return;
        }
        if (i==tbl->len)
        {
            i=0;
        }
        if (((tbl->table[i]).flag==1) && ((tbl->table[i]).key==key))
        {
            --(tbl->num);
            (tbl->table[i]).key=NULL;
            (tbl->table[i]).val=NULL;
            (tbl->table[i]).flag=(-1);
            return ;
        }
        else
        {
            ++i;
        }
        if (i==j)
        {
            return;
        }
    }
}
//Вывод таблицы
void htbl_open_out(t_htbl_open *tbl)
{
    FILE *fin;
    int i;
    fin=fopen("output.txt","wb");
    for (i=0;i<20000;++i)
    {
        if (tbl->table[i].flag==1)
        {
            fprintf(fin, "%i - %i", tbl->table[i].key, tbl->table[i].val);
            fprintf(fin,"\n");
        }
    }
    close(fin);
}

void main()
{
    FILE *fin;
    int i,j,val;
    t_htbl_open *TBL;
    fin=fopen("input.txt","wb");
    for(i=0;i<20000;++i)
    {
        j=rand() % 100000 +1;
        fprintf(fin,"%d",j);
        fprintf(fin," ");
    }
    fclose(fin);

    TBL=htbl_open_new(20000,hash_func);
    fin=fopen("input.txt","rb");
    for(i=0;i<20000;++i)
    {
        fscanf(fin,"%d",&j);
        htbl_open_add_rec(j,TBL);
    }
    fclose(fin);
    htbl_open_out(TBL);
    htbl_open_del(TBL);
}
