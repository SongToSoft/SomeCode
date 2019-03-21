#include <stdio.h>
#include <stdlib.h>

typedef size_t t_htbl_key;
typedef size_t t_htbl_val;

typedef (*t_htbl_hash)(t_htbl_key);

typedef struct t_htbl_list_rec
{
    struct t_htbl_list_rec *next;
    t_htbl_key key;
    t_htbl_val val;
}t_htbl_list_rec;

size_t hash_func(t_htbl_key key)
{
    return key;
}

typedef struct
{
    t_htbl_hash hash;
    size_t len, num;
    t_htbl_list_rec **table;
}t_htbl_list;

t_htbl_list *htbl_list_new (size_t len, t_htbl_hash hash)
{
    size_t i;
    t_htbl_list *tbl;
    tbl = (t_htbl_list *)malloc(sizeof(t_htbl_list));
    tbl -> table = (t_htbl_list_rec*)malloc(sizeof(t_htbl_list_rec*)*len);
    tbl -> num = 0;
    tbl -> len = len;
    tbl -> hash = hash;
    for (i = 0; i < len; ++i)
    {
        tbl -> table[i] = NULL;
    }
    return tbl;
}

void htbl_list_del (t_htbl_list *tbl)
{
    size_t i;
    t_htbl_list_rec *tblU, *tblR;
    for (i = 0; i < tbl -> len; ++i)
    {
        tblU = tbl -> table[i];
        while (tblU != NULL)
        {
            tblR = tblU -> next;
            free(tblU);
            tblU= tblR;
        }
    }
    free(tbl -> table);
    free (tbl);
}

t_htbl_list_rec *htbl_list_get (t_htbl_list *tbl, t_htbl_key key)
{
    size_t i;
    t_htbl_list_rec *tblU;
    i = tbl -> hash(key) % tbl -> len;
    tblU = tbl -> table[i];
    while (tblU != NULL)
    {
        if (tblU -> key == key)
        {
            ++(tblU -> val);
            return tblU;
        }
        tblU = tblU -> next;
    }
    return NULL;
}

t_htbl_list_rec *htbl_list_add (t_htbl_list *tbl, t_htbl_key key)
{
    t_htbl_list_rec *tblU;
    if ((tblU = htbl_list_get(tbl, key)) == NULL)
    {
        size_t i = tbl->hash(key);
        tblU = (t_htbl_list*)malloc(sizeof(t_htbl_list));
        tblU -> next = tbl -> table[i];
        tblU -> key = key;
        tblU-> val = 1;
        ++ (tbl -> num);
        return tbl -> table[i] = tblU;
    }
}

void htbl_del_elem(t_htbl_key key,t_htbl_list *tbl)
{   int i=tbl->hash(key);
    t_htbl_list_rec *tblU, *tblR;
    tblU= tbl -> table[i];
    if (tblU== NULL)
    {
        return;
    }
    tblR = tblU->next;
    if (tblU->key == key)
    {
        tbl -> table[i] = tblR;
        -- (tbl -> num);
        free(tblU);
        return;
    }
    while (tblR != NULL)
    {
        if (tblR -> key == key)
        {
            tblU-> next = tblR -> next;
            -- (tbl -> num);
            free(tblR);
            return;
        }
        tblR = tblU -> next;
    }
}

void htbl_out (t_htbl_list *tbl, FILE *fout)
{
    t_htbl_list_rec *tblU;
    int i;
    for (i = 0; i < tbl -> len; ++i)
    {
        tblU = tbl -> table[i];
        while (tblU != NULL)
        {
            fputc(tblU -> key, fout);
            fputc(tblU -> val, fout);
            tblU = tblU-> next;
        }
    }
    return;
}

void main()
{
    t_htbl_list *tbl;
    size_t len=20000;
    int i,j;
    FILE *fin;

    fin=fopen("input.txt","wb");
    for(i=0;i<20000;++i)
    {
        j=rand() % 100000 +1;
        fprintf(fin,"%d",j);
        fprintf(fin," ");
    }
    fclose(fin);

    tbl=htbl_list_new(1500,hash_func);
    fin=fopen("input","wb");
    for(i=0;i<20000;++i)
    {
        fscanf(fin,"%d",&j);
        htbl_list_add (j,tbl);
    }
    fclose(fin);

    //htbl_list_del(tbl);

}
